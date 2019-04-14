package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.Comment;
import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.demo.Repository.CommentRepository;
import kr.hs.dgsw.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp1 implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("aaa","abc", "123",
                "D:/PORTFOLIO/DGSW/3Grade/WebPractice/WEB_20190326/upload/2019/04/08/120f4f83-c197-41ad-a9ae-c7a5940534a4_126735264BF78B7749.jpg",
                "120f4f83-c197-41ad-a9ae-c7a5940534a4_126735264BF78B7749.jpg"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = null;
            if(found.isPresent())
                username = found.get().getUsername();
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }

    @Override
    public CommentUsernameProtocol viewComment(Long commentId) {
        Optional<Comment> comment = this.commentRepository.findById(commentId);
        if(comment.isPresent()){
            Optional<User> user = this.userRepository.findById(comment.get().getUserId());
            if(user.isPresent()){
                return new CommentUsernameProtocol(comment.get(), user.get().getUsername());
            }
        }
        return null;
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment) {
        CommentUsernameProtocol cup = null;

        Optional<User> user = this.userRepository.findById(comment.getUserId());
        if(user.isPresent()){
            cup = new CommentUsernameProtocol(this.commentRepository.save(comment), user.get().getUsername());
        }

        return cup;
    }

    @Override
    public CommentUsernameProtocol updateComment(Comment comment) {
        CommentUsernameProtocol cup = null;

        Comment found = this.commentRepository.findById(comment.getId())
                .map( c ->{
                    c.setImagePath(Optional.ofNullable(comment.getImagePath()).orElse(c.getImagePath()));
                    c.setImageName(Optional.ofNullable(comment.getImageName()).orElse(c.getImageName()));
                    c.setContent(Optional.ofNullable(comment.getContent()).orElse(c.getContent()));
                    c.setUserId(Optional.ofNullable(comment.getUserId()).orElse(c.getUserId()));
                    return this.commentRepository.save(c);
                })
                .orElse(null);

        Optional<User> user = this.userRepository.findById(found.getUserId());
        if(user.isPresent()){
            cup = new CommentUsernameProtocol(found, user.get().getUsername());
        }

        return cup;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        try {
            this.commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
