package persional.cheneyjin.cloudisk.controller;

import java.util.List;

import org.apache.tools.ant.taskdefs.Ear;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserFileInterface;
import persional.cheneyjin.cloudisk.model.UserFile;

@Controller
@RequestMapping("/share")
public class ShareController {
	UserFileInterface userFileInterface;

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public @ResponseBody int shareFile(int file_id) {
		try (MS ms = new MS()) {
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			int shareTag = userFileInterface.shareFile(file_id);
			if (shareTag != 1) {
				return 0;
			} else {
				ms.getSession().commit();
				System.out.println("SHARE_FILE");
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@RequestMapping(value = "/userShareList", method = RequestMethod.GET)
	public @ResponseBody List<UserFile> getFilesInShare(@RequestParam("user_id") int user_id) {
		try (MS ms = new MS()) {
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			return userFileInterface.selectFilesInShare(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/cancelShareFile", method = RequestMethod.GET)
	public @ResponseBody int cancelShareFile(@RequestParam("file_id") int file_id) {
		try (MS ms = new MS()) {
			userFileInterface = ms.getSession().getMapper(UserFileInterface.class);
			int cancelShareTag = userFileInterface.cancelShareFile(file_id);
			if (cancelShareTag != 1) {
				return 0;
			} else {
				ms.getSession().commit();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 * @RequestMapping("/") public String shareFolder(){ return ""; }
	 */
}
