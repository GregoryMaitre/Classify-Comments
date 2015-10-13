package Model;

import java.util.Arrays;

public class Comments {
	private Comment[] comments;
	private int commentNumber = -1;
	
	public Comments() {
	}
	
	public Comments(Comment[] comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Comments [comments=" + Arrays.toString(comments) + "]";
	}
	
	public Comment getNextComment() {
		commentNumber++;
		return (commentNumber < comments.length) ? comments[commentNumber] : null;
	}
	
	public boolean hasNext() {
		return commentNumber < comments.length;
	}
	
	public int size() {
		return comments.length;
	}
	
	public int getIndex() {
		return commentNumber;
	}

	public void setCommentNumber(int index) {
		commentNumber = index - 1;
	}
}
