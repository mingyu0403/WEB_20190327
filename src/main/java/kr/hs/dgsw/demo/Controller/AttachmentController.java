package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.Comment;
import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Protocol.AttachmentProtocol;
import kr.hs.dgsw.demo.Repository.UserRepository;
import kr.hs.dgsw.demo.Service.CommentService;
import kr.hs.dgsw.demo.Service.UserService;
import kr.hs.dgsw.demo.Service.UserServiceImp1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;


    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile){
        String destFilename
                = "D:/PORTFOLIO/DGSW/3Grade/WebPractice/WEB_20190326/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();

        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        try {
            // defalut 사진
            String filePath = "D:/PORTFOLIO/DGSW/3Grade/WebPractice/WEB_20190326/upload/2019/04/08/120f4f83-c197-41ad-a9ae-c7a5940534a4_126735264BF78B7749.jpg";
            String fileName = "120f4f83-c197-41ad-a9ae-c7a5940534a4_126735264BF78B7749.jpg";

            if(type.equals("user")){
                User user = this.userService.viewUser(id);
                filePath = user.getProfileImagePath();
                fileName = user.getProfileImageName();
            } else if(type.equals("comment")){
                Comment comment = this.commentService.viewComment(id);
                System.out.println("getImagePath: " + comment.getImagePath());
                System.out.println("getImageName: " + comment.getImageName());

                filePath = comment.getImagePath();
                fileName = comment.getImageName();
            }

            File file = new File(filePath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            resp.setContentType(mimeType);
            resp.setHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");
            resp.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, resp.getOutputStream());
        } catch (Exception e)                                                                                                                                                  {
            System.out.println(e.getMessage());

        }

    }

}
