package persional.cheneyjin.cloudisk.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import persional.cheneyjin.cloudisk.db.FileInCloudInterface;
import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserFileInterface;
import persional.cheneyjin.cloudisk.model.FileInCloud;
import persional.cheneyjin.cloudisk.model.UserFile;
import persional.cheneyjin.cloudisk.utils.CloudFileUtils;
import persional.cheneyjin.cloudisk.utils.Contants;
import persional.cheneyjin.cloudisk.utils.MD5;

@Controller
@RequestMapping("/user-file")
public class UserFileController {
	private UserFileInterface userFileInterface;
	private FileInCloudInterface fileInCloudInterface;

	@RequestMapping(value = "/getFileInfo", method = RequestMethod.GET)
	public @ResponseBody UserFile getFileInfo(@RequestParam("file_id") int file_id) {
		try (MS ms = new MS()) {
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			return userFileInterface.fileInfo(file_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public @ResponseBody int deleteFile(@RequestParam("file_id") int file_id) {
		try (MS ms = new MS()) {
			// System.out.println("DELETE_FILE!");
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			int actionTag = userFileInterface.deleteFile(file_id);
			if (actionTag == 1) {
				ms.getSession().commit();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody UserFile uploadFile(@RequestParam("file") MultipartFile uploadFile, HttpSession session) {
		if (!uploadFile.isEmpty()) {
			try (MS ms = new MS()) {
				int userId = Integer.parseInt(session.getAttribute("USER_ID").toString());
				int folderId = Integer.parseInt(session.getAttribute("DIRECT").toString());

				CloudFileUtils cfUtils = new CloudFileUtils();
				cfUtils.analysiSize(uploadFile.getSize());
				cfUtils.analysisFileName(uploadFile.getOriginalFilename());
				System.out.println(uploadFile.getOriginalFilename());
				String md5 = MD5.getMd5ByFile(uploadFile);

				// upload and check fastupload
				FileInCloud fileInCloud = new FileInCloud();
				fileInCloudInterface = ms.getSession().getMapper(FileInCloudInterface.class);
				int equalsReturn = equalsFile(md5, fileInCloudInterface.selectFileMD5(md5));
				if (equalsReturn != 0) {
					fileInCloud.setId(equalsReturn);
				} else {
					fileInCloud.setMd5(md5);
					fileInCloud.setPath(Contants.UPLOAD_PATH);
					FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File(Contants.UPLOAD_PATH + md5));
					fileInCloudInterface.setFile(fileInCloud);
				}

				// info insert to database
				UserFileInterface userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
				UserFile userFile = new UserFile();
				userFile.setUser_id(userId);
				userFile.setFileName(cfUtils.getFileName());
				userFile.setFileType(cfUtils.getFileType());
				userFile.setFileSize(cfUtils.getFileSizeStr());
				userFile.setFolder_id(folderId);
				userFile.setFile_id(fileInCloud.getId());
				userFileInterface.uploadFile(userFile);
				ms.getSession().commit();
				return userFile;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private int equalsFile(String uploadFileMD5, List<FileInCloud> list) {
		for (FileInCloud fCloud : list) {
			if (fCloud.getMd5().equals(uploadFileMD5)) {
				// System.out.println(fCloud.getId());
				return fCloud.getId();
			}
		}
		return 0;
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam("file_id") int file_id, HttpSession session) {
		UserFile userFile = null;
		FileInCloud fileInCloud = null;
		try (MS ms = new MS()) {
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			userFile = userFileInterface.fileInfo(file_id);
			fileInCloudInterface = ms.getSession().getMapper(FileInCloudInterface.class);
			fileInCloud = fileInCloudInterface.getFile(userFile.getFile_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int loginUserId = (int) session.getAttribute("USER_ID");
		// System.out.println(userFile.isShare());
		if (downloadInterceptor(loginUserId, userFile.getUser_id(), userFile.isShare())) {
			// System.out.println(fileInCloud.getMd5());
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", new String(userFile.getFileName().getBytes("UTF-8"),"iso-8859-1"));
				
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(Contants.UPLOAD_PATH + fileInCloud.getMd5())), headers,
						HttpStatus.CREATED);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean downloadInterceptor(int loginUserId, int userFIle_user_id, boolean isShare) {
		if (isShare) {
			return true;
		} else {
			if (loginUserId == userFIle_user_id) {
				return true;
			}
		}
		return false;
	}
}
