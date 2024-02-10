package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;

@Service
public class CommentService {

	@Autowired
	CommentJpaRepository commentJpaRepository;
	
	public boolean saveComment(Comment comment) {
		Comment result = commentJpaRepository.save(comment);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	public List<Comment> getComments(Long postId){
		List<Comment> commentList = commentJpaRepository.findByPostIdOrderByRegDateDesc(postId);	
		
		return commentList;
	}
	
	public Comment getComment(Long id){
		Comment commentList = commentJpaRepository.findOneById(id);	
		
		return commentList;
	}
	
	public boolean deleteComment(Long id) {
		Comment result = commentJpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		commentJpaRepository.deleteById(id);
		return true;
	}
	
	public List<Comment> searchComments(Long postId, String query) {
	    List<Comment> comments = commentJpaRepository.findByPostIdAndCommentContaining(postId, query);
	    return comments;
	}

}
