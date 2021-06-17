package persional.cheneyjin.cloudisk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import persional.cheneyjin.cloudisk.db.FolderInterface;
import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.model.Folder;
import persional.cheneyjin.cloudisk.model.UserFile;

@Controller
@RequestMapping("/folder")
public class FolderController {

	private FolderInterface folderInterface;

	@RequestMapping(value="/direct",method=RequestMethod.GET)
	public @ResponseBody Folder direct(@RequestParam("folder_id")int folder_id,HttpSession session) {
		try(MS ms = new MS()){
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			Folder folder = folderInterface.selectFolderById(folder_id);
			session.setAttribute("DIRECT", folder.getId());
			return folder;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public @ResponseBody int createFolder(@RequestParam("folder_name") String folder_name, HttpSession session) {
		Folder folder = new Folder();
		try (MS ms = new MS()) {
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			// 避免GET参数被JSP本身做的字符串转换为8859-1
			folder.setFolderName(new String(folder_name.getBytes("iso-8859-1"),"utf-8"));
			
			folder.setFa_id(Integer.parseInt(session.getAttribute("DIRECT").toString()));
			folder.setUser_id(Integer.parseInt(session.getAttribute("USER_ID").toString()));
			if (folderInterface.createFolder(folder) == 1) {
				System.out.println("CREATE FOLDER OK! COMMIT!");
				ms.getSession().commit();
			} else {
				// ms.getSession().rollback();
				return 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return folder.getId();
	}

	@RequestMapping(value = "/set-folder-own", method = RequestMethod.POST)
	public @ResponseBody int setFolderOwn(@RequestParam("user_id") int user_id, @RequestParam("folder_id") int folder_id) {
		try (MS ms = new MS()) {
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			if (folderInterface.setFolderOwn(user_id, folder_id) == 1) {
				ms.getSession().commit();
			} else {
				// ms.getSession().rollback();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	/*
	 * @Override public int copyFolder(int folder_id) { return 0; }
	 */
	// update folder user_id to null
	// service should set user_id and delected folder_id into session for
	// rollback.
	@RequestMapping(value="/deleteFolder", method=RequestMethod.GET)
	public @ResponseBody int deleteFolder(@RequestParam("folder_id") int folder_id) {
		try (MS ms = new MS()) {
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			if (folderInterface.deleteFolder(folder_id) == 1) {
				ms.getSession().commit();
			} else {
				// ms.getSession().rollback();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	// get user_id and delected folder_id from session
	@RequestMapping(value="/recovery-folder",method=RequestMethod.POST)
	public @ResponseBody int recoveryFolder(int user_id, int folder_id) {
		try (MS ms = new MS()) {
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			if (folderInterface.recoveryFolder(user_id, folder_id) == 1) {
				ms.getSession().commit();
			} else {
				// ms.getSession().rollback();
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@RequestMapping(value="/chFiles",method=RequestMethod.GET)
	public @ResponseBody List<UserFile> selectChFiles(@RequestParam("folder_id") int folder_id) {
		try(MS ms = new MS()){
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			//System.out.println(folderInterface.selectChFiles(folder_id).size());
			return folderInterface.selectChFiles(folder_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/chFolders",method=RequestMethod.GET)
	public @ResponseBody List<Folder> selectChFolders(@RequestParam("folder_id") int folder_id) {
		try(MS ms = new MS()){
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			return folderInterface.selectChFolders(folder_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
