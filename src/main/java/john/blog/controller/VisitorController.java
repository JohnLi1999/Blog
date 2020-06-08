package john.blog.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import john.blog.domain.Visitor;
import john.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@Controller
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @RequestMapping("/visitorSignUp")
    public String visitorSignUp(Visitor visitor) {
        Random r = new Random();
        int random = r.nextInt(8) + 1; // integers in [1, 8]
        visitor.setImage("/images/thumbs/1-" + random + ".jpg");

        visitor.setUsername(visitor.getUsername());
        visitorService.saveVisitor(visitor);

        return "redirect:/login";
    }

    @RequestMapping("/sendSMS")
    @ResponseBody
    public String checkSMS(String nationCode, String phone, HttpSession session) {
        String username = nationCode + "-" + phone;
        boolean notRegistered = visitorService.findVisitorByUsername(username) == null;

        String json = "{}";
        if (notRegistered) {
            String code = sendSMS(nationCode, phone, session);
            json = "{\"code\": " + code + "}";
        }
        return json;
    }


    /**
     * Send a verification message to the specified phone number
     *
     * @param nationCode nation code of the phone number
     * @param phone      phone number
     * @param session    session field
     * @return           the verification code
     */
    private String sendSMS(String nationCode, String phone, HttpSession session) {
        // Set values used in Tencent SMS Service
        int appId = 1400317703;
        String appKey = "f62c5ac697a80527f2a083e60e1aa4d4";
        int templateId = 536333;
        String sign = "JohnLi公众号"; // Use "Content" in the Sign Section

        String[] params = new String[1];
        Random r = new Random();
        String verificationCode = "";
        // Generate verification with 6 random numbers
        for (int i = 0; i < 6; i++) {
            verificationCode += r.nextInt(10);
        }
        params[0] = verificationCode;
        // Save the generated verification code into Session Field
        session.setAttribute("sms", verificationCode);

        // Send message
        System.out.println(verificationCode);
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
//        try {
//            SmsSingleSenderResult result = sender.sendWithParam(nationCode, phone, templateId, params, sign, "", "");
//            System.out.println(result);
//        } catch (HTTPException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return verificationCode;
    }
}
