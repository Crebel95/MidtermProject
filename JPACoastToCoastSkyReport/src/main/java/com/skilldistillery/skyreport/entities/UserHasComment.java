package com.skilldistillery.skyreport.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class UserHasComment implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="comment_id")
	private int commentId;

	@Column(name="comment_vote")
	private Boolean commentVote;

	public UserHasComment() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Boolean getCommentVote() {
		return commentVote;
	}

	public void setCommentVote(Boolean commentVote) {
		this.commentVote = commentVote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, commentVote, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHasComment other = (UserHasComment) obj;
		return commentId == other.commentId && Objects.equals(commentVote, other.commentVote) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserHasComment [userId=" + userId + ", commentId=" + commentId + ", commentVote=" + commentVote + "]";
	}
	
	
}