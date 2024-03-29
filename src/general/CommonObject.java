package general;

public class CommonObject {
	private String name;
	private int index;
	private String comments;

	public CommonObject(String name, int index, String comments) {
		this.name = name;
		this.index = index;
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
