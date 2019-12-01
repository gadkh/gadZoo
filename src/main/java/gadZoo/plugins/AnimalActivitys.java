package gadZoo.plugins;

public class AnimalActivitys {
	private boolean feed;
	private boolean clean;
	private boolean sleep;
	private boolean play;
	
	public AnimalActivitys() {
		super();
		this.feed=false;
		this.clean=false;
		this.sleep=false;
		this.play=false;
	}

	public boolean isFeed() {
		return feed;
	}

	public void setFeed(boolean feed) {
		this.feed = feed;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public boolean isSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}
}
