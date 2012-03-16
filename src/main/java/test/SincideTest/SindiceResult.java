package test.SincideTest;

public class SindiceResult {
	
	private String title;
	private String link;
	private String res;
	
	public SindiceResult(String res) {
		this.res = res;
		create(this.res);
	}

	private void create(String res) {
		title = find(Tags.title, res);
		
	}

	private String find(Tags tag, String res) {
		for()
		return null;
	}
	
}
