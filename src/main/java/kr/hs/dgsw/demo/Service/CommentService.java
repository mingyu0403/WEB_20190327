package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.Comment;
import kr.hs.dgsw.demo.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {

    List<CommentUsernameProtocol> listAllComments();
    CommentUsernameProtocol viewComment(Long commentId);
    CommentUsernameProtocol addComment(Comment comment);
    CommentUsernameProtocol updateComment(Comment comment);
    boolean deleteComment(Long commentId);
}
