package john.blog.controller;

import john.blog.enumeration.UpdateType;
import john.blog.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /** Return the User page */
    @RequestMapping("/user")
    public String user() {
        return "user.html";
    }

    /** Obtain blog owner's information */
    @RequestMapping("/getOwner")
    public String getOwner(Model model) {
        updateUser(UpdateType.NONE, null);
        return "user.html";
    }

    /** Update username */
    @RequestMapping("/updateUsername")
    public String updateUsername(String username) {
        updateUser(UpdateType.USERNAME, username);
        return "/user.html";
    }

    /** Update password */
    @RequestMapping("/updatePassword")
    public String updatePassword(String password) {
        updateUser(UpdateType.PASSWORD, password);
        return "/user.html";
    }

    /** Update personal sign */
    @RequestMapping("/updatePersonalSign")
    public String updatePersonalSign(String personalSign) {
        updateUser(UpdateType.PERSONAL_SIGN, personalSign);
        return "/user.html";
    }

    /** Update avatar */
    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(String base64) throws IOException {
        System.out.println(base64);

        // Save image
        String path = ResourceUtils.getURL("classpath:").getPath() + "../resources/static/upload/avatar/";
        File fileLocation = new File(path);
        if (!fileLocation.exists()) {
            fileLocation.mkdir();
        }

        // Replace image extension from "jpeg" to "jpg"
        if (base64.indexOf("jpeg") != -1) {
            base64 = base64.replaceFirst("jpeg", "jpg");
        }

        // Get file name (base64.substring(11, 14) --- jpg)
        String avatarName = UUID.randomUUID().toString() + System.currentTimeMillis() + "." + base64.substring(11, 14);

        // Save useful data in base64 file
        String imageBase64 = base64.substring(22);

        // Convert base64 file to byte data
        byte[] buffer = Base64.decodeBase64(imageBase64);

        // Use FileOutputStream write the file
        FileOutputStream outputStream = new FileOutputStream(path + avatarName);
        outputStream.write(buffer);
        outputStream.close();

        updateUser(UpdateType.AVATAR, avatarName);

        return "redirect:/user";
    }

    /**
     * Helper function to update user information
     * @param type           - the updated field type
     * @param updatedContent - the content updated to
     */
    private void updateUser(UpdateType type, String updatedContent) {
        switch (type) {
            case USERNAME:
                userService.updateUsername(updatedContent);
                break;
            case PASSWORD:
                userService.updatePassword(updatedContent);
                break;
            case PERSONAL_SIGN:
                userService.updatePersonalSign(updatedContent);
                break;
            case AVATAR:
                userService.updateAvatar(updatedContent);
            default:
                break;
        }
    }
}
