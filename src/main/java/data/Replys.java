package data;

public class Replys {
	String id;
	String moimId;
	String writer;
	String ment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoimId() {
		return moimId;
	}

	public void setMoimId(String moimId) {
		this.moimId = moimId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMent() {
		return ment;
	}

	public void setMent(String ment) {
		this.ment = ment;
	}

	@Override
	public String toString() {
		return "Replys [id=" + id + ", moimId=" + moimId + ", writer=" + writer + ", ment=" + ment + "]";
	}

}