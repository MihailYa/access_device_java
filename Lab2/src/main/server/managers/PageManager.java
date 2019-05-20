package main.server.managers;

import java.util.ResourceBundle;

public class PageManager {
	private static PageManager instance = new PageManager();

	public static PageManager getInstance() {
		return instance;
	}

	private static final String BUNDLE_NAME = "pages";

	public static class PagesIds {
		public static final String DEFAULT_PAGE = "defaultPage";
		public static final String ACCESS_DEVICE_PAGE = "accessDevicePage";
	}

	private ResourceBundle resourceBundle;

	private PageManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public String getPage(String id) {
		return resourceBundle.getString(id);
	}

}
