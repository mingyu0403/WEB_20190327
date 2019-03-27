package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.Comment;
import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.demo.Repository.CommentRepository;
import kr.hs.dgsw.demo.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/listallcomment")
    public List<CommentUsernameProtocol> listComments(){
        return this.commentService.listAllComments();
    }

    @GetMapping("/viewcomment/{commentId}")
    public CommentUsernameProtocol viewComment(@PathVariable Long commentId){
        return this.commentService.viewComment(commentId);
    }

    @PostMapping("/addcomment")
    public CommentUsernameProtocol addComment(@RequestBody Comment comment){
        return this.commentService.addComment(comment);
    }

    @PutMapping("/updatecomment")
    public CommentUsernameProtocol updateComment(@RequestBody Comment comment){
        return this.commentService.updateComment(comment);
    }

    @DeleteMapping("/deletecomment/{commentId}")
    public boolean deleteComment(@PathVariable Long commentId){
        return this.commentService.deleteComment(commentId);
    }

}
