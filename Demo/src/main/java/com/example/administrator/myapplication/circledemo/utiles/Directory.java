package com.example.administrator.myapplication.circledemo.utiles;


public enum Directory {
	/** 产品名称. */
	PRODUCT_NAME("lazy"),

	SDCARD_PATH(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/"),

	/** 系统运行所需资源根目录. */
	ROOT_PATH(SDCARD_PATH.toString() + PRODUCT_NAME + "/"),

	/** 数据库文件路径. */
	FILE_DB(ROOT_PATH + "database.db"),

	/** 日志目录. */
	DIR_LOG(ROOT_PATH + "log/"),

	/**缓存图片的目录 */
	CATCH_PHOTOS(ROOT_PATH + "catch/"),
	
	/** 日志配置文件路径. */
	FILE_LOG_PROPERTY(ROOT_PATH + "logging.properties"),
	/**保存任务图片的路径*/
	CACHE_VIEWSIMAGE(ROOT_PATH+"mediaTemp/"),

	/**缓存的h5界面的路径 */
	CATCH_H5(ROOT_PATH + "h5/");
	private String dirStr = "";

	Directory(String dirStr) {
		this.dirStr = dirStr;
	}

	@Override
	public String toString() {
		return dirStr;
	}

	private void setDir(String dir) {
		this.dirStr = dir;
	}
}
