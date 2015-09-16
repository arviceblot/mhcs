package com.mhcs.joshua;

public class ImageURLs {

	final public static String PLAIN_URL = "images/modules/hallway.png";
	final public static int PLAIN_INT = 0;
	final public static String DORMITORY_URL = "images/modules/dormitory.png";
	final public static int DORMITORY_INT = 1;
	final public static String SANITATION_URL = "images/modules/sanatation.png";
	final public static int SANITATION_INT = 2;
	final public static String FOOD_WATER_URL = "images/modules/foodstorage.png";
	final public static int FOOD_WATER_INT = 3;
	final public static String GYM_URL = "images/modules/gym.png";
	final public static int GYM_INT = 4;
	final public static String CANTEEN_URL = "images/modules/canteen.png";
	final public static int CANTEEN_INT = 5;
	final public static String POWER_URL = "images/modules/power.png";
	final public static int POWER_INT = 6;
	final public static String CONTROL_URL = "images/modules/control.png";
	final public static int CONTROL_INT = 7;
	final public static String AIRLOCK_URL = "images/modules/airlock.png";
	final public static int AIRLOCK_INT = 8;
	final public static String MEDICAL_URL = "images/modules/hospital.png";
	final public static int MEDICAL_INT = 9;

	final static public int RIGHT_ARROW = -1100;
	final static public int UP_ARROW = -1200;
	final static public int LEFT_ARROW = -1300;
	final static public int DOWN_ARROW = -1400;
	final public static String REDSQUARE_URL = "images/map/transred.png";
	final public static int YELLOW = -10;
	final public static int GREEN = -20;
	final public static String FULL_MAP_URL = "images/map/marsmap.png";
	final public static String BLANK_IMG_URL = "data:image/png;base64,iVBORw0KGgoAAAANSUhEU"
			+ "gAAAAoAAAAKCAYAAACNMs+9AAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAA"
			+ "AB3RJTUUH3QQdARsYjh+1KAAAAB1pVFh0Q29tbWVudAAAAAAAQ3JlYXRlZCB3aXRoIEdJTVBkLmUHA"
			+ "AAADklEQVQY02NgGAWDEwAAAZoAAQuinR8AAAAASUVORK5CYII=";

	final public static int MAP_ROWS = 65;
	final public static int MAP_COLS = 100;
	final public static int MAP_X_1TO1_SIZE = 10;
	final public static int MAP_Y_1TO1_SIZE = 10;

	/**
	 * returns proper map image for row and col
	 * 
	 * @param row
	 *            Y location of map
	 * @param col
	 *            X location of map
	 * @return string of the map image location in the outer directory;
	 */
	public static String mapImage(int row, int col) {
		return "images/map" + MAP_X_1TO1_SIZE + "x" + MAP_Y_1TO1_SIZE
				+ "/MarsMap_" + ((row * MAP_COLS) + (col + 1)) + ".png";
	}

	/**
	 * This is to work with what is passed by get module urlStr by the Module
	 * class.
	 * 
	 * @param module
	 *            String of the module name
	 * @return module string name
	 */
	public static String moduleURLByName(String module) {

		if (module.equalsIgnoreCase("plain")) {

			return PLAIN_URL;

		} else if (module.equalsIgnoreCase("dormitory")) {

			return DORMITORY_URL;

		} else if (module.equalsIgnoreCase("sanitation")) {

			return SANITATION_URL;

		} else if (module.equalsIgnoreCase("food/water")) {

			return FOOD_WATER_URL;

		} else if (module.equalsIgnoreCase("gym/relaxation")) {

			return GYM_URL;

		} else if (module.equalsIgnoreCase("canteen")) {

			return CANTEEN_URL;

		} else if (module.equalsIgnoreCase("power")) {

			return POWER_URL;

		} else if (module.equalsIgnoreCase("control")) {

			return CONTROL_URL;

		} else if (module.equalsIgnoreCase("airlock")) {

			return AIRLOCK_URL;

		} else if (module.equalsIgnoreCase("medical")) {

			return MEDICAL_URL;

		} else {

			return ERROR_URL;

		}

	}

	/**
	 * give in the paramaters to get the arrow URL
	 * 
	 * @param direction
	 *            direction with const
	 * @param color
	 *            of arrow with constant
	 * @return strin of arrow URL
	 */
	public static String getArrow(final int direction, final int color) {
		String URL = "images/map/";
		String hue = "green";
		String dir = "UP";
		String ext = ".png";

		if (color == YELLOW) {
			hue = "yellow";
		}

		if (direction == DOWN_ARROW) {
			dir = "DOWN";
		} else if (direction == LEFT_ARROW) {
			dir = "LEFT";
		} else if (direction == RIGHT_ARROW) {
			dir = "RIGHT";
		}

		return URL + hue + dir + ext;
	}

	/**
	 * This is to work with what is passed by get module urlStr by the Module
	 * class.
	 * 
	 * @param module
	 *            String of the module name
	 * @return module string name
	 */
	public static String moduleIdToURL(int id) {

		String urlStr = ERROR_URL;
		if ((id > 0) && (id <= 40)) {
			urlStr = PLAIN_URL;
		} else if ((id > 60) && (id <= 80)) {
			urlStr = DORMITORY_URL;
		} else if ((id > 90) && (id <= 100)) {
			urlStr = SANITATION_URL;
		} else if ((id > 110) && (id <= 120)) {
			urlStr = FOOD_WATER_URL;
		} else if ((id > 130) && (id <= 134)) {
			urlStr = GYM_URL;
		} else if ((id > 140) && (id <= 144)) {
			urlStr = CANTEEN_URL;
		} else if ((id > 150) && (id <= 154)) {
			urlStr = POWER_URL;
		} else if ((id > 160) && (id <= 164)) {
			urlStr = CONTROL_URL;
		} else if ((id > 170) && (id <= 174)) {
			urlStr = AIRLOCK_URL;
		} else if ((id > 180) && (id <= 184)) {
			urlStr = MEDICAL_URL;
		} else if ((id > 0) && (id >= -40)) {
			urlStr = "images/modules/ghosthallway.png";
		} else if ((id < -60) && (id >= -80)) {
			urlStr = "images/modules/ghostdormitory.png";
			;
		} else if ((id < -90) && (id >= -100)) {
			urlStr = "images/modules/ghostsanatation.png";
			;
		} else if ((id < -110) && (id >= -120)) {
			urlStr = "images/modules/ghostfoodstorage.png";
			;
		} else if ((id < -130) && (id >= -134)) {
			urlStr = "images/modules/ghostgym.png";
			;
		} else if ((id < -140) && (id >= -144)) {
			urlStr = "images/modules/ghostcanteen.png";
			;
		} else if ((id < -150) && (id >= -154)) {
			urlStr = "images/modules/ghostpower.png";
			;
		} else if ((id < -160) && (id >= -164)) {
			urlStr = "images/modules/ghostcontrol.png";
			;
		} else if ((id < -170) && (id >= -174)) {
			urlStr = "images/modules/ghostairlock.png";
			;
		} else if ((id < -180) && (id >= -184)) {
			urlStr = "images/modules/ghosthospital.png";
			;
		}

		return urlStr;

	}

	/**
	 * This is to work with what is passed by get module urlStr by the Module
	 * class.
	 * 
	 * @param module
	 *            the type number in the list of numbers
	 * @return module string url
	 */
	public static String moduleTypeNumberToURL(int typeNum) {

		String urlStr = ERROR_URL;
		if (PLAIN_INT == typeNum) {
			urlStr = PLAIN_URL;
		} else if (DORMITORY_INT == typeNum) {
			urlStr = DORMITORY_URL;
		} else if (SANITATION_INT == typeNum) {
			urlStr = SANITATION_URL;
		} else if (FOOD_WATER_INT == typeNum) {
			urlStr = FOOD_WATER_URL;
		} else if (GYM_INT == typeNum) {
			urlStr = GYM_URL;
		} else if (CANTEEN_INT == typeNum) {
			urlStr = CANTEEN_URL;
		} else if (POWER_INT == typeNum) {
			urlStr = POWER_URL;
		} else if (CONTROL_INT == typeNum) {
			urlStr = CONTROL_URL;
		} else if (AIRLOCK_INT == typeNum) {
			urlStr = AIRLOCK_URL;
		} else if (MEDICAL_INT == typeNum) {
			urlStr = MEDICAL_URL;
		}

		return urlStr;

	}

	// placed at the bottom for size
	final public static String ERROR_URL = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAEJGlDQ1BJQ0MgUHJvZml"
			+ "sZQAAOBGFVd9v21QUPolvUqQWPyBYR4eKxa9VU1u5GxqtxgZJk6XtShal6dgqJOQ6N4mpGwfb6baqT3uBNwb8AUDZAw9IPCENBm"
			+ "J72fbAtElThyqqSUh76MQPISbtBVXhu3ZiJ1PEXPX6yznfOec7517bRD1fabWaGVWIlquunc8klZOnFpSeTYrSs9RLA9Sr6U4tkc"
			+ "vNEi7BFffO6+EdigjL7ZHu/k72I796i9zRiSJPwG4VHX0Z+AxRzNRrtksUvwf7+Gm3BtzzHPDTNgQCqwKXfZwSeNHHJz1OIT8Jj"
			+ "tAq6xWtCLwGPLzYZi+3YV8DGMiT4VVuG7oiZpGzrZJhcs/hL49xtzH/Dy6bdfTsXYNY+5yluWO4D4neK/ZUvok/17X0HPBLsF+v"
			+ "uUlhfwX4j/rSfAJ4H1H0qZJ9dN7nR19frRTeBt4Fe9FwpwtN+2p1MXscGLHR9SXrmMgjONd1ZxKzpBeA71b4tNhj6JGoyFNp4GH"
			+ "gwUp9qplfmnFW5oTdy7NamcwCI49kv6fN5IAHgD+0rbyoBc3SOjczohbyS1drbq6pQdqumllRC/0ymTtej8gpbbuVwpQfyw66dqE"
			+ "ZyxZKxtHpJn+tZnpnEdrYBbueF9qQn93S7HQGGHnYP7w6L+YGHNtd1FJitqPAR+hERCNOFi1i1alKO6RQnjKUxL1GNjwlMsiEhcP"
			+ "LYTEiT9ISbN15OY/jx4SMshe9LaJRpTvHr3C/ybFYP1PZAfwfYrPsMBtnE6SwN9ib7AhLwTrBDgUKcm06FSrTfSj187xPdVQWOk5"
			+ "Q8vxAfSiIUc7Z7xr6zY/+hpqwSyv0I0/QMTRb7RMgBxNodTfSPqdraz/sDjzKBrv4zu2+a2t0/HHzjd2Lbcc2sG7GtsL42K+xLfxt"
			+ "UgI7YHqKlqHK8HbCCXgjHT1cAdMlDetv4FnQ2lLasaOl6vmB0CMmwT/IPszSueHQqv6i/qluqF+oF9TfO2qEGTumJH0qfSv9KH0n"
			+ "fS/9TIp0Wboi/SRdlb6RLgU5u++9nyXYe69fYRPdil1o1WufNSdTTsp75BfllPy8/LI8G7AUuV8ek6fkvfDsCfbNDP0dvRh0CrNqTb"
			+ "V7LfEEGDQPJQadBtfGVMWEq3QWWdufk6ZSNsjG2PQjp3ZcnOWWing6noonSInvi0/Ex+IzAreevPhe+CawpgP1/pMTMDo64G0sTCXI"
			+ "M+KdOnFWRfQKdJvQzV1+Bt8OokmrdtY2yhVX2a+qrykJfMq4Ml3VR4cVzTQVz+UoNne4vcKLoyS+gyKO6EHe+75Fdt0Mbe5bRIf/wjv"
			+ "rVmhbqBN97RD1vxrahvBOfOYzoosH9bq94uejSOQGkVM6sN/7HelL4t10t9F4gPdVzydEOx83Gv+uNxo7XyL/FtFl8z9ZAHF4bBsrE"
			+ "wAAAAlwSFlzAAALEwAACxMBAJqcGAAANj9JREFUeAGF3QmwplWZH/DTd+m+vS8gDTTLbWloFkXRFmSRZQIoKjhYlrG0amJMhXKSmDJU"
			+ "MWUlpMLUJFaqoqlKZMiM0RpqJIaIBHVQ2cQgKpsgIDTI2jS90Q290t23l3s753e++//65dLOHD193vecZ/0/z1ne9/vux7R3v/vdBw6U"
			+ "A2Vg2kCZNm1aUdK2m8l7fenXDgz06A9Ubvfabgnt1L70o08NTcZyr9WXSqcyMTHR1xeewcHBvk3pQ+vamDI+Pl7279//Fv7Ybzx6tHSg"
			+ "75a9e/eW7du3lzfffLO4JmvunLll8ZGLm248Fcr6/7diQUYXn6n2dceGmhHlrWDrO5ShMS4CIyi0VUr1qqc8MkKTezJcpzYnOgZ36abSZi"
			+ "y8f1+LV0FDRwBmT6rx2J5gR0f6tYpAvPbaa2XXrl3tPv1r1q4ps2bPKgsXLuzJrcFoODSqnn6XoZ/sfktDZ0ovIJ2ODDQDqy3TBg4dLArU"
			+ "gIIP7e7du8vY2FiZO3duGRoa6mdjaCM/bQDoGtVkTbEpekKPJrO0XdcZPjDYm0Fd59ELRuzttpGJP/aljR68b7zxRtm4cSOyMn369Obzg"
			+ "YkDZf/4/ta3bdu2smDBgmZP5LeBzj/kHaqg75ahZEY6w9iMnQxG+tD8fQobT1W8bt26Mn/+/LJo0aIyZ86cxmP6Zzy6usY0HWyu9nVtch"
			+ "3g0eBptJW0K08wsjRlPHoyO/S7Ji+6QxtZ6SfLkmRWCMjw8PBb7JqYVuUcGCgzZ84sO3fubLQjIyNvW+YiP0kRm6LHPZrctxmiMx3aLiD"
			+ "pD2O3zXWUop01a1YLxObNm5txZozs4VCMCn3439LWJMg9um7tAkmWMX2q2ai41m+8W/UpkdeW+dqV/q5sdHv27GmzQjAALUDBglwFj377y"
			+ "dSEi9xGWP/p8kfO1BZtC0gGdDRBZlHPfl39gs546LvXIdJ3+GGHl61btzZaa696xBFHtGUM78SBCqb/BSQzcVKhPpWzATf66AgPB0PjOnx"
			+ "o0KdmfwVi5KCduvEajwwAr1+/vs0OM4D86Nu3bx8VZcaMGS0IkdnlbwSH+Ce2p+3b2KEdSrT1xaCpbegJiLDQpw2PTJkxMqMcfvjh5fXXX"
			+ "2+zRbatXr26BcUyxrk/5ECC0G2jP7pjR3RmPG1A0gp+7sMfukO1lin7xaZNm9pyxA7FnjF9ZHqzXcAy4/mbxLBsxq+u7OhPm7G+PZK/Tjr"
			+ "3bZ4jDABd4vQZ79JEsDag9IVXAfoFxNpqybLBK7Jux44d5cgjjyyzZ89udFMTIjIbQ/0nDpJvLDZlXBtAjKemP3T6I6tL4zo+7Nu/r2x8b"
			+ "WPZsGFDf5kylsOJxHKtLzrdZ/Z4dMg4GqXrX2zo6kfT1ofJpXrA4NQSBq0M6Art0kZp6DPmXgYdddRRjZ/B9hYbvAC99NJLLQPJjgN4OYk"
			+ "2tQt+dMQe9+xKK7NV46EhM2CxdWo13krFjpwtW7a0pLEc4eODlp5ZM2e16737erMDTWyfPjy9nb4SpK7dUzGiJ8V1auurQ2/b1A0QwogI0"
			+ "1Ki9AVKgIOy21j+Cb9Z8I53vKNtevg5yAmz5JVXXimOi/aWefPmtX780dmu7SuTYMVwLZrUgB/7wp/xBA1dt8SPgLd5y+aybu26fiDwC4Z"
			+ "EIkMZGK8Hhvo//UpsGZk50nzTF/3RG7rYn/vYFztaWyHuHU1QTZYIdIsoBnfH9TPM/1PCF0VpLVcyL/cCos+9/t27dpeFixa2JW7O3Dmtn"
			+ "/wkRDM0SmrbtacbhPSTq2gDSmTpJy81/A4gr776aptZMt+zBtAtUXidEl17QsfLh8xEOHgwlGyRhyb2uCZDjX4t+4wloWL32wLSuKb8g3F"
			+ "q0UfIoYxAqx9Npr2N0Iauz4Mjh+O0zd9JzPhhhx3WsjIGR6979LHFPWfc52FNX0rGGj1bqz2RGXDQbt+2vR042JdA4EHrhKXN0V3ymEmWR"
			+ "jK0Nbz92Z1+cmNLTmVkG6fHWPDRnwDT2wIS5m7runvfVeJaieGha87XfvcMzrilK1OfQYplyp6yZs2afpbY9J37Fy9e3LIST1eWQHKAM5y"
			+ "zRy1ZsqRlt5NRnOSgVxmyXaDRAkZ1jU6xZJoZAB+ZMdJsNpajrpOVk5PZwW7vrDa8tqHR448dDikSid7YZFwC4bNnvvDCC+Xoo49uSzibz"
			+ "Da25KDjWYdv084444wDMTDARpn7VH1KQO7d9cAPf8bC0w2QjGUwIwDLANcMW7t2bQtKsggtMDnntIZPgI877rjynve8p9kkABdffHEbf+q"
			+ "pp8rvf//79uwDTDNtdHS0zU4nPaADny5P3gDZsH5DWfXKqhYks5jNllK87BIENiZAbJJEzz//fNsD2adIti9/+cvlXe96VwuA/vhvHAZke"
			+ "QcmQMYlhkDRwzb7qUR0uuu/OukKIch9gHavBGDXxhVKct068k8ddgzEo6JjfFpP8sCSWQIBYIGKQ4JgKZPpZsGxxx5bzjvvvHL88cf3HdI"
			+ "nu1es+EDrFzTrPX2uBUBA2ffss8+2Pq90gPPCiy80S7P206eyT1Dw5JWIVqAByD4BxedwwjYHF3JT8KpkKTncZNWAq3FBXr58ebNXkF588"
			+ "cWDm3qEaJW07Wbyny5N9zo04UmbADIM6ABiOIMArQ+glij3xgVm/br15dTTTi1Lly5t09yS8M53vrPRyawsXbKergSRnABFLoeBhwYfhz2"
			+ "g0gNkS6YgjO0ea3xZquLPnDmz6+W0FkA6BRJwgFU9t0iKBIO/8Z0M1wE/PHxGp9+1EpwEt7+pR5g2kW3UnX+iTJvrDHfvIyt0lDPIEgA8p"
			+ "yuZK5s5ChyOAsSy8cEPfrBdn3LKKY2eoaqpT5bMHBzoHUmB4amaDPIU8mUzWZYCoAN/2bJlLaj4ZS0aL0JlOLsSbDPTOMDIsFQpWfbQ8sf"
			+ "zh0SBl2Chj8/6+JQ+9wlO9zpYSR6lv2S1u/oPgYiULshRpCX4D9F0+bo8rm3SKieBwXH9yUzAmSmWjGOOOaY5LXAAQyOzZT7AEzz6OB4Ag"
			+ "CXIlkTX6PCQ59oeQgd6G6olEL1NFyhZwsh0TRdeyYCOjfrRSgC+GEOvT9KhgZGSgGjTTzf8yFFcs9Fsfsspq41OEgAKYUDNmLYbDOMpudY"
			+ "mGzLGCODKLIHQMhDI5AHGfqICwQOj4BnjTJYLGQ/UE088sfVzhD5BxAcU98AmXxDoyowQJMuiGQVo+h988MGmz8zRz9acrsiz2bIjiSCZV"
			+ "LLNUEExri8Y4NNn1qETMPagIV8bv2CE3vgQppQIS1/ujbtOTfSngh76qf3k6WMU52Qao4Dj1MQwAXCKEgTXnABibNHKxGSj7AKsAkhLkkA"
			+ "JkOBpASgYeDlMJp10myVA+d3vflfe+973NuCcdvSRl0Czjd0T470ld8/Ynv6bYjqTNOxxPdV391MLe1LxwA1/S9KAqA1zty/XEdr9BDFP6"
			+ "oSjI1Cb+z5P7VMsI5wFqqntXG7W1KN3MwhIeAGABjhduUAyBlCtNR34ZgOg8eDX7+k7QadLcS8wMlqfvcemDBSzVsCee+65ppcc+rVwweu"
			+ "a/06P9OeITBdABU+Wu0Yffe4V/IpZCSv8bMHDT7X/EW6C0Tgm/0GQ/jBUtj7w0w5U8Ov/lGbspNA+bb1X3DMgmccIm7SzO0DmzZ9XFi5Y2"
			+ "OjoS2ZzGi+gAaklAwCcsBwIBmA5Ty4Q3WvxoEdHboDK8pdZKNDAQeNEJ1hmMl6zmW8BePac2RWBae2B1vKq8A2dlg52SxDXZLJFYQ9dOTx"
			+ "4KJaEZho/W0Ax/EMFcUqirC/VWALCcDKNad1bl4FF+RNPPNEMc4KyRJ1wwgltaWIsHmBxQBapli3ZG3DJEhhOAQytPk5ZwtjhaAtsgESOA"
			+ "KIBnOIeAIqWnWTZV7ROVmQ4heHRh0513HXQUI0BVj+ZZpuAosevn1/kC1Jmq+ApeNBKMv687djbqOo/BBCkaAN4Aqgv1wlSI578x5hq+Rg"
			+ "dHS3nnntuWblyZQMPiSdURjEQcIwBLAAZ6DqfzctWAeE8BwTE0znQ0JoR7LGHGKdTwASTH/gApcULGPfsI5d+PrjX7yirWmLZwxZyZD470"
			+ "QN66dKljU4C0MkGCSUQqiRUyc6scM9GBQ3/3ZttfOkvWZgoZVQKBcqhWnRqNxiHojv77LPbxm2vABiDOGiJABgnHfdMZc5YrtyTbcxGi0/"
			+ "Bq5gx9HIEDceAIjAcE2jXApAHUdf60QoCX5MM8YV842SyRTCGh3pH3PiKl24HD/L4cPLJJ7fkYbcxQQeuwh66GtjVXgEnQ4DIZxfcQj+EO"
			+ "MUAxVNLgNbveuo9nvSRpwL2/PPPb++bGMhZy5PsND1DD2y0Mo+DAmOcwe5ffvnl5qTZUH1twWivGKpz9h9y6DObAKLIYE4LksADheNkCBB"
			+ "Q8AA+1/j0CQS9fCJv0+ubWuADGPvZ6EUjG1etWtUeNr13syTjp8uYWSP57Et4JBDZWjMs15LAOP39JYtjXWDdi3aAY7ASGv0Z6/YRKgA27"
			+ "TPPPLMpMa5fNr3//e8vTz/9dJvqHBYILYc5o6KlG8gCZgyoW7duaZnPeH0ezKzfnk0EkYPGtOSqnFcBL1AAokNg0AFOP5380aeQr0/Qnby"
			+ "MuRdE/vgiBxr28dd+IukEJQHROnQIFh502szMzJT4Q0f7kgOiFJ3utUBR3Odav6p0264M1872MpKDeIHGARnrdAUQb3kFI4Yaz35ABrAtT"
			+ "+QA0RTXcsB7Kw4DUyYC3zWAAcdZsjmPzoZuxgBUsuhnVzLVPVvZQIaxnICyjBmn55xzzmlHdrYowca4pCNTEvCRDeyjn29ksk8128jGxzd"
			+ "yKt/Bx3zCgYxYiyCgY8p4+qbeAxG41teTTjqpOYaWgwoHyLSf2DCBzRBGccIYUNHZ9M0QhZOWNbIBCjzZ6FW4B0tZevfdd7c3p8bxs1dQn"
			+ "LzYZWbQJSHYg0YLFGCRLRhq/NZHL1n64sepp57afCBbH7zYTxZ/Xat8yXLFjwQitmnDCwO6+5s6QZQy3rXqWp/iWtGfkuvQ62cIgwVFhht"
			+ "jmMDLVIoBZR8AkDGFMZETnbKRXlmFhwNmmgdJy9WTTz7Zjs50uScLDZC2btnaQHZNp6AaI1vWkpdEYBf7AqJ79gAYn8QJP1stQWYt+8kXX"
			+ "EkigGTwgzw6tPRIQDYaT2DIcs0m/JKpH5BuIBAS2m271wFMH74o4IRpevrppxtq4Fm79WWjZhBQGWsqMzhAaRlFvpYD2poeDRR6yLHkaQF"
			+ "iGVixYkXrswSiAdKON3c0Ha6VJEYbq4HW4k+/YJo97AsWbHcQsUSSrQ3ovoViZgmGfrYnuehLQPTTwyc4sE+hQ4GDgl5QBhikIggR5oCuD"
			+ "WDda0LwdWkZKxg2OGDLNAajQctZWe8oaz3nCCNW1ZMKMLxKeeihh9rsoZMMfDUeLWMF0jHaBi/L7SOWBDNIxpJpaZLRkkMl37LDDjK1gGO"
			+ "Ta2Ch96xj86Yj/tJ9xOIj2rHWMkuWYuYPDfceNPkETL6TaSZpyVAFnh7FTCGT/uCtHa92omNvvT+4gceQxl3/wfyHavgyO4Bvs/RZBjnAZ"
			+ "qgAAIkzjEfnKOt1d3g54JMzPKa2vYFjX/ziF9tbYOCTZQlx5hcE2Vb/tqX/YpAMepII7hWtWWY/2vzG5uZ49i5AAUFrP8C7d++eZgeAZa/"
			+ "PXSSKvcpTvIKWrbGfbXAiC9juXesDNDzocI2H3GDt1dNgpdfPzv6SFeCbxs4//1B/AsMApw/LDIPdd8cEA7COgQzjnCVBhsaB537/XF8z2"
			+ "ksuuaQ59dWvfrUFFGAcMUNGR0dbAtAH7DwI0hvdhKEHEp0+QzcTBdarG/1PPvFke5dGtoAJqgCwL4CjkwSCSw5w0bOb/GzmgujebI0NwJe"
			+ "M5Jp9fIYpuuBT51LzG23t6y1VGUwAtEru282UfzLGSOf1pfVVgswRaQoVcgVDda0wmGEcRidjb7zxxrL61dVtXUbzgQ98oGUSoM0KIAieK"
			+ "gieZwTGUuNbHU5aihmY2UFfjq6cNeuM5+0w0Hfu2tkAZc+usV1t2TODLX9mK5AVoDs8nHbaae2EZf8gywogMJYjvkgkRRDiIz8FlQ14YEQ"
			+ "unAQuLf7+B1SMjyNpCXYd4N3nOuC6J9wxl+JMZf0U6OMMetfWYtnCEUVfM6Q6xWHXQPRQael65JFHGo/3YAAlhzyZ7pTleOwLDPYJ48lMI"
			+ "AFF8KKLL/glhH0A4IJKJ7A87AFHggmwo7VE4gudbLU/kisAfCUTjwCRQRc7+ICXLrNJcS8w0RF8jNFBVn/JCsAGESKYWg7VRzjlsrg5ViN"
			+ "OsGtjjGC8lqEMN8Zp2cJRoAEA3aKFi8rw9OHy05/+tDkiSGYJxy666KLy8Y9/vGUeGfQCBxAJvJlEl366HFEFxyzS79MC384XOEFU2EkGW"
			+ "wCm8BWQshyfexiRhddMJd9e5l6SGRMk12SpgspXffjJQkeffnLZDjN9A266wYgxabtBwOQ+FS8hZod1l2HjFSjFrHHP6AQBiHjiEKcAyGl"
			+ "vVxmf04v11vQHOr2WRMdbr+zd4+MQAGW5CgC+6KMT0AoeBwBACLYsjw18IUcA8arksMmYpEmhVwEq2e4lE9340PKJ3a5V+AgamWYPuiQLG"
			+ "+kgRz9s+jMkSrWIphZ9qZhVghjnrO6ekWgYISD6OI9G5ghInEKDXl9mjlmgHy2QOGBZwuM7WYJm3J5juVEBB2wbex5EgZ1gPPPMM+2kJrC"
			+ "j9SCgnwwZC5gkCTAaIDVoSmxCK/gANc4XOs0uAQaqMX7TLyAONfglGFuM088nY5KXTxIHn/7cD9avxlwXoBmS67TpA263zz1jPQucddZZz"
			+ "VgZwXhGAJPDvryAL4EQKBlkHBiKNtf4XeNhKFpyvvSlL7WTj35FMBRjHBZMy543AO5914p9vqzgIZJtSQpBASyb2Krlj6+OugY8MIGKLn5"
			+ "bUgVDP7rcxyY87OGD5Y4cex1a+h0ijLELHT8FBV38b+mAQYlgxikYuyWG6XONb7RmnWmbJUqraJfWUxcHgNqWi6rcvSlucwc4HWQxSNZGJ"
			+ "6AYC5BPfvKT7XRDjqUsdrCTHvdsMBvJlqX2ifz1lHFBYC96tgiO+8hii2yfPtjbf8gWWBVgeNioGmOba8CyVUUrSGwxE1bVB168cKBPUNg"
			+ "oKK4TWDLIJK+/ZDFMiYFdY/VhUEOHlgPWZ0oBSUnWSX2Uk8MZJVOTAc715AGYM4BnkIJeRW/f+PSnP910CRgaNdlOFr36vOMySyxzvogg+"
			+ "OTQwz62sCvFvUo3MAFkppHpgymgy3r8Cln4IxO9/UGwJYtgqBLCl/Iy23M4YH+SjM2wdK8ll55+QGJkQHHPWMQqYq3CIAKBasnSzyDne2s"
			+ "ro2QFg8mjFD2lHJZBlhHrKiBkLTrAoKWLbnouu+yylvWcRgPYzCQtWhVwXtt4LmFDEoQcfGRpVfa6785CdpAjCYyzRcsetLkWLP6Rg5ZPx"
			+ "qLDEkV3fMiscY9P4Yt+/PrxKuxpAXHD8JQQpL87RjlwKfVlNVkIUMKzwTEa8IKElgGCg1b2aI0HLGN4OIeWU6N1KXR6o8MziODZzD13kGH"
			+ "22UfYylHPIjZ3R2PB9n0rMgGbAKFjNx5+AINOgKuhZ19sQoMWnX4yBEkly4rANv7AAajkmx1k4mdDims0ZCbY6GHRbAkhAp2qa1WZeq3PM"
			+ "gWo973vfU0IBd5j2bSAJDDkAJeRAIxyRjLeHuI6WUMuh/GO1mB4rvEMwQEHA6ele+65p73z0mep8xkIW8iyLLAD75VXXtlkCxL77SsJRGZ"
			+ "A9OrHx745c2ZXvuFmExtlMX762IpGn4JHHxDJRM9P4OpPG5okA3381KaGvh+QBn4FsGpvBjSN9Z8EI5kEYEe2yy+/vB9RwhlEuHVdKxiyj"
			+ "SKZwpFUSwu5HDMTjOMn25igCgZ+Tum3TksAy5H9gfN49FmmZDOHLYVkC5ClDt9vfvOblsVoyJfR7BN4fUCggx0gIMdYMlswYjtf2a0PvWt"
			+ "4WAlivzb+G3cNE77wSSDdw4Yu/WygD23/lMWRtnXV1oDS+ianbO4TTYKNJwBRygiFIn0M5BCZHAKSalkBKiPxmEn6ZTyg8KXiJU8A7FM+k"
			+ "7fZe0GIh3y61Bw37W8f+tCHWnC80vcahg6y2a30gtBbEYDMDrK07AISWlV/ZkqCazkmE63ZocBHIUMCsAng2siJfj7RkaDo73E3EfWfSUN"
			+ "zC4iAgVHBRICAqIxTKNTPcBWYjGSMYgOWwca6jjoeGkOXY2sM5zCZZKPxIRFHOa3SgT/LQZxjFz4zyQwSNLK9NBQEdgRctrlmFzkK/WrAp"
			+ "MusAbxKLvmC0QUaVu7jo+vIQ8/e4KjNNRrjdPYD4gaB1gPS+ERv6UGonzICAcLwAKJfQZOp554chhkHbAJInoIfPXlasm2O+DjiHnjuPYW"
			+ "vqmd677RUMuwP6PDYtC1F3WWIDjNQSUDIssdJIj7k2QCfoEQ3m/HqEwD86NmKVmuc3XgS0PDr51/GYMNW9yr74aHASAmO/YCkg7ADA3UaT"
			+ "7x16SIIjWlIGYP0BVgt4BUKXaMnL8V1shC/JQzogCFLX7IKwO5t3pYpLxlV9E5bqiDJev2yPoEHgI2cHUODvW9/mCGKFoAH6tiOyU2YXXi"
			+ "UzC622ePMhu7sSODQs49OAQIs21X38Ag27OAfGmNNf8UGPpk1+hue/jGgplCW/vS5V/LcQVBOSqYuR9AkE/RRHFl4XTNK5bBxcjilz7IEa"
			+ "BlJjqCoNnkzwUOfgAgqsIDu/ZH9AahL67MPOragAQQ7VBuvwNOHj/6J6rMxfegByKYALPn0u3eNx+yiix1ay3JmGH349cMzgeC3Sj7bEix"
			+ "46Y+d7utfUJlC4Dq4dhpQE6jcJ8IcMJUJY5A/7TLGMOB2DdPPCI4rjFUZhV9QBaLtN/XjUg6hVy0vMpQucmz4+LxKx8sOR2MgraqzxXssp"
			+ "zQyBNRsyB7XBdm1zZ9O8tjGZjONDxIC+HSylf4sc3xgG/2SwslPcNiJPiXY4WcP+XjQaGFqLC0+dtQ/2Hl7IDB0BbpXCQW4MYIJcM2JgJj"
			+ "+ZAFFARk/AxRtHBUU4Pr0Dp9slOlmo2AplgdOAxwICt3j+8eb/tH6/GFps8d4JgI6XpWdZgg7VCc8/tDDbi29gqACSuUXf8jKDKKTrfgU/"
			+ "YKrXwKgV8iTFCp68vQZD77BrzFM8gwuP3n5dcBRAzwCCvQRorrWJ4tkBscoAlSyCR8DAK9ypEJPmqFmPOcFgoFo0JPLQTrihDG0apxpQuo"
			+ "/wAU0evqzjLFDn6RhI0DZadw9PWaFVj/7JIMxs4IeAJPDRteSwOwIiGxgKx2WQLLwbdu+rf9BGn5+BDO+KF1sco2GbFi2GeJbFUoGDOZen"
			+ "4hSSLGatVgGBwynHIFK4BiMB3/9f+vXp5IfefRwmlwO0mXPsJErgAIGUIBLHjo8qkJebKcfGJxzTd/Mkd6DHj5ygGiMPDLwAp8/ZAHFvXF"
			+ "tEoSNrrXRKynIQ7fk6CVt5vkBA5/bCDTZfFVcs4cdZLBBQkgMOJJNf/sr3DiUdqqT+lWCVEbkFQlj3FNGiYpWVmhjDMUK5QzpBhowwDf1O"
			+ "ck4wLimz5jlK0vRaF2eQp/1WxBS6SHTvQCbZWk9MOpX2CRIdADKZo9W4QccVDbHf60xfcDWCp6C30zLB2WSFH3w5DO7YEUnO9qsqPLIavi"
			+ "FQUf3OsZoMRvXEggwQcgs0c8wCuMIpQlKZOyvfOjwo1Pw4gswxgRMwIHt3p4BUPKs05KAHYBIAC1FZi8nYx9dfMLHVuNA068YQ0sOnYoZx"
			+ "t4EDa8yFQ/3AoqfD/wBrhk9WhNGUOix53WxxYcWj34+SgZ6+DVYv9ZyHSLGqYq225drQpx0soRoCRIcAKkKOgEhxzh+xjn7c5gj+np7S48"
			+ "GPRC0kcFZfeSRxXDLGWAByLHpM3pfouAMXlWA8bqmx73nFDTsARob2GvMNXnJdEDpx6sGF3RK7NKSp5oZfKTT7IUNO/WZdZGVNh+ewYMdf"
			+ "MHb/mAnSimOciCk6nPt41BGxxmGE5gli2HoCOeQ4h5dsoKB6BU0w8O9PYsNaI0LAvCaztqvJDBmRALHUba1zKo6gMIG9/QBXnGtPyDjz4w"
			+ "wTjdQ4j+9sT/jxvCpAsFOLSw8HwkAGWbJvr29j4gdye2vTn6WSjYoeEaGel/O1ke2voZTjGiU9R/3iWLGECqYBMC9MRmLVn9AQmcMTZRxU"
			+ "FXiDJDQuM+4Pk65J49cMjhJpgwCpGv7jWUMPfCyFOGxdjdgqgw60NCDDi/w6MjMpgOdZEtC6KNLG98ShPhiTCWXbejQOL7PH+6d2iyVksg"
			+ "SbLmFFx4tf2MXfja0hZyRqpLrtJhUQjgm+yhO0ASFEbIRCGgVRrqnRCEvwLvHFx1kGUPLAcDYEwBmLMYCG08cwkO3Fq0KbDRadPoC9ozKv"
			+ "2tSJhry8AZQfa7JzNoenRljT3SS657N6NgMIxjAyDh5c+fNbb+cJ4nQ0qkY55ugBLehgIsIQYp7yhiiVcwO1xljBKcBTzF+wrVoXFNIRma"
			+ "TTFFimPEAo5/8LCf6kwRAUMhlM6Cjm358fiJQMOlVOGnvQK9vrNL5iigZKvn4yKGHLfrZn2qcnPgS2XgUmPBPxQ+HLLfkK/mhHD7YV9hIr"
			+ "oqefYrrIZnggkAlwnNPscpQiighOIL0AUQfIAGPPoYb45BqTOWs9V+Lj0HGXbPHPX2KPrK0eDhjCeAM58jLJu0VjoBbs4EDZHzWccUSZ5w"
			+ "utmotb2Twl3z7UOTxkR52otWyKwHmGzxUMhR4KOThNxZ84i+76TKGTj8fyW4BMb0pTBC0iaAWIZBjoKxwzzCGuFZMSfSEU4LemKnMAHLJ0"
			+ "p9sQqsCKEZ5HTK2p/e5PWeyX5DhG4zs5Th5nAIgHY7HwBdU8tiQpcw1nQLEbnxkGHet6AcWncZUY8GGr2iSJPxQ2UEOnZFlT+M7fyVG5ON"
			+ "X+RTcjcGNfUMN0PrDKgMzDh59ExBOuNYSzECtCjxGBJQEMPQMw6dfVjIijiXbyOAcOuOKa0dCY4BJ4CSBe3/swxFPwh4Wge9DKrPCu6zYQ"
			+ "2fAiU1kKWTTk4QxrrLLDEn2Co5ZFd/4y173ZAsAPuCy30lKH1vJUhUY05X72CF5EpS+DcDdPba7zJ5bj4h1lQiIBImwe4VCRkSRMUpirFa"
			+ "Jo+jCb8wyQzkZDIqR5LpWkm0AU+Kwe3YCQ7VsoSXLt1AAZ9wpRitwdGoHh3rBRosnM0LWuu/6ARTjADXrPE+wlU524jETlWQ93QIiGPjYl"
			+ "jGy3AtwHmzjPzthJChJAHLbqxPEphgDOcKwbssYf9fn1YX+0BEOrDhFQfgExn0A8IY1D0+c4LDCQTKAz3EAMJYcVR+HtfShox9dNuiccsi"
			+ "lE3988LNKNf/bTNKPn9zYl8CQJ2mMk8NuYwGZPDgk+93DzTMI/QFVmwQKTeTgp4du1+xxzb/sW+17WWZIMjZgYCBY8edc/lAFs9J3ts4Sz"
			+ "gEhjgqOcdmpcIwRimWEceRyWvbgBTZ6fAqZwOGwJYlNshUvO8eqTDPDNUfoRO/DLZlqaWVrFxg0ZJFtphpjMxvYJ8gShi62qOSTK5nIox8"
			+ "9Xr54PSIAZqY+svGr9MUGfsRXAeVnMNLio6MFmzEEyFSGEiQY1uis06Ojo024DMWcihe9e8o51RTUr3H6XSlAG2O8MeABK1mMV2EI/ezgI"
			+ "P3ASJD04RVE8gFZEWgA2jfw2dDZ728V830stMYULT3kkkOevgQPDRuNsZmvxtnATwkBVPw+45do9MEo/rNLYJTgQg5aQaHfvYKHfL6yUz8"
			+ "M2imLccCTQc7tfoVhtAaBwgbwpIFNUv2HsvQHtCjRrypaigSAU+TJMn/w+atf/ap87GMfa/fojKFRGRrA3HPSxk1WMg0Pm1XZhcfXgoy7B"
			+ "xywjRtDr2ULmwHkPmP0ZwaZKejIYK97CWe9Z4fAeS1CPiwEgr0BO1iwnV6y+EBegqfftWqM/JasDMbgpZ3vPfkZJUfLMDCYAVFOCXqFY8b"
			+ "ca/EocRQtJxkKALpkuy+XWQI5AghZySgyFHLJZGRkMBw4MkpBi8c4+fY4n737qhAafeyRlehSG3P9Bzjs7ILER3x0KWxlg8y1HLJfcCQtX"
			+ "rTko4ntjbH+I1Bs06JF45osfijpY6cx90N+z9yHOFd+6sr2jW3MMsFUVDAzEEhaiqNIH1CBhy+K0aHhoNY44xkjkzhqFmaKx0CgZQp3EwA"
			+ "dg63n/kNcfhac8cCMbPSWBl+iIwMPRyMPHflsZJ+N2NqPRj9fyIgudksmyWKM3+jpJYPN6MklQ9WvuE4g9Knu6TXG7tDpa3w1lx0+hpadu"
			+ "Kz9RSugnPExUmbdX7p0ad9A/YyRJYo2wqIsCo1nTAYBkiGMR8sRTgLQuFa/5YZxQCQ/DodW6zUEoDiFTpAzC1zbS4wDnA0AZnc3MJ7OPcM"
			+ "oxvFniSQj8htQlUYg2EYGma613ZKACG4S1jXd/CYr8roBM+5+2nhNjPpAPO0LX/jCAd/i4KzlY7TuHU4qlPtwxbpsQ2O4PgV4CQiBAtids"
			+ "gkGAyjD52jnGpBausJLlj4ytPjocM0u/GroOOwaDVpyBE8L0Oh1T4YWKMlmn6F4zRIdZEdP+pygLIFm89SZGAzITqVTABIQGGTMNRuMRT4"
			+ "Z9PLF50TVkzJWfRrylX9fNvPBE6d8D9ZnwvYTmU2R2eI5hUMRQrh72RLHY4B7ihXGADpLgezL35ULuHGy8JKNj5E5lchcGRwHyYxs1+zr1"
			+ "iy1sQWNawWfCmCzcipA6NhqjzCD3JMt4TLTotsYm4zrI4v93X74KGjSn3t9GWfL/vpNUfKGHBNthP469bFHH2uMTikyRhAYI+scL2W1oDE"
			+ "As+wBmGt9qZQLimIshWL8/rwA4AxyhJTt7oGRQJOhz94DDLQqGjI4pCT46PUBW3HNHm2qQAMtGzfb8PGVHHokGJ/ZgY8+Y10fyY8+NOzXk"
			+ "k+G4lphM/nkKfhUH2dXpkZf07Ds31H/uqq6NGR5+vznP99mydLRpe1XnhlgnXUaYhBjBImhCYZMFCBjjNEfZVFsTGUUIBgLDDIFkiOCqp/"
			+ "RkYPWNXDZorhXFSceS6BEsc8FYGNoYocWvz4tefZJf25mTIKYfcbotO/EFjbrZz/fEiA69CnkdsfIjP7QoIsMba/2Ztf+umfgnzFUX1L6z"
			+ "GRn/eymCrkOGH5lBzGDZSZHGS7SKvDz2YKjn30ljhKKVwkIUR4j6eC8YHBWlisAcK/ftX4Ap4aHDkUrIMDFZzm16aIHGnqFLAUNWzIzJJr"
			+ "Eykzgi+RAp/IJL3u6hR/xSX/u6ZRY/MOfMfYIqDJYP/pG77/yU39+qfWR1bDhV/1ye93U6vep6zbAOX+ZZG1n3G9/+9v2FX6ZS5nKYA4Ag"
			+ "SJLmX5CKeWE+4DmumdIL8Nco01hCFq8nBBwMiIvwSJHv0q/e8DSL7sVwBlnFzvJJC/6jNGRfjJyj15BH5sEA63SQJy0lb1q+kKvjw52sSU"
			+ "JQX+jbZLqclaDotT/LF6VU/Ga/PJgmVZn7ub6kcH9D5R1P/lJGfLEifk73/lOWz48sB0/enxzjiLgAMAGzxmbHYNlRNdptO5jeFpGyWKzD"
			+ "o89i9OCq5CFNsEGVngzRrZrY+yJXfj1KfgFCShozQLXbGaXLLbMuQeacXIAqRpHJ0j0xw6y0fHDuGttin68eNKPF13lrK2ZUW0vNenqi85"
			+ "qQPsd+YmBGWX9xi3l3vvuL0fd+sNyQn0GOfofX16GHn/88cbsQQ3w9g2zw3pKsAAAkrOcAEgAk1nAVfQxQpuMA4gHK7PPjwskCE51rmW9f"
			+ "YDszEgy6OEo8MiIo2TrU9C5psv19773vfZHouQCCP9HPvKR1kqGvHrhl8IPNIJndvk8hTx7Iz36jZOvP7QJChrXxlynxMYEtC1TlW7f2M5"
			+ "qV51lB0bK2nWvlQceeajceuvtZee+kXLaYfPLf/3za8v0sXrQcex1/GSo7LBP2Oitte4pDJBAU2QUxTFYH8NjoGtLoTeywHeeF1Qg/PrXv"
			+ "24Pb0AUDEdg+j/60Y82vYDA508MBErA8er30Oc4zg4gSxZZn9clEgot2+677772d+uZ2d4+2BPJ45MSIPkhYFnC2mD9Rz87Fdcp8RN/qnH"
			+ "XXZ7BGojd1Z499WefBGjLzvFyz713l+/8r9vqB4I+fxopJ9VHjFd37ih/8R//U/lMfSE75NW6LLR0mR0CYkozHPCyO2BmuRAgymUWmmRNs"
			+ "gXQlkDfczUu4/3xZT5iFSx6bMYePIHm13+8j/Ie7ec//3n7WSaJIdvZIEE8rKmK2aX6jIZsH0S5Z5dAWib9HHkCBFi62M7eFNdovMsDWhd"
			+ "4PO7VZDx/Qifx6NNKGLIqpnW8fvC1v77u3+23s+zDA+XB3z5VvvU3N5UNm+rRf3r9Y9EjD68fDO6ry9basnD2vPJXP3y0nHXDtb3nEI6YI"
			+ "U5SwFMYSXkyX5Ao1KcyUisoCuO71RIAlCuuuKJ8+MMfbrT0kCG7ARPn6RIUP9Tv78s57G8DydMvEWQv+7TGgA7wLG9z5/Q+s0dj1gi+E6N"
			+ "Zg5+ddEs0PtEfYCWQCgM0QKZbm2DwEeiSTpKQZwydPrJ6e5avJNWvtI7XRB2sr6NWrSn/55b/W375+Op6ipoox9Qkw7dzx9b6o/5Vfn0GmRj"
			+ "cXz7x2cvLLX93T+85hBOWAcbEEGB3DeKUe8YkM9Drp6DrBJpkK0PJRyu7H3jggfaQST5d9C6tR2xLp99rzIzgHJmefzw8mgU+nev+KpClkC"
			+ "32P6CQR59Zc/XVVzd+wTYb9ZOVPQmtZUxgAc2ePBDTm9muBT56fqr6lOCV6x07ttck3Fa/3lq/21VXunvuuqPcee9jZe3rr5Xl7xxtP3Y5X"
			+ "l+ODg35GNufW3h7Xfchfz64p/5s7fz6FwSMddylUOEgJxgZ5bKKQwwx5vtNfkkzsybfU0XPQQG49tpr+3+CZn2+8847yze/+c22nwAyWWZZ"
			+ "u/3225usz33uc+XSSy9tG2t0W6rI9KbAE74sBljsYWdqgAN6ZiM6vuEhM4VNq1at6vuOx35mVuMVIH3d5BMAMshLgMjTj9YBxhL14ksbyo03"
			+ "3VZeWv1GTY4jy7Ljjqr666eS0/3OV305e6C+e5teP/3cVb+XUJ8/hqtZ29/cVmbMrd/+SfZ2M4ITFJvGFFsWON2CUae6j1CNpzJeEVJ9lglL"
			+ "C8csT9/+9rfL1772tfKVr3yl9QHXkmZTNjvwmGnf+MY3GihXXXVVA55NDLa+s9OvAkkg9z4VBKBroJlpDgeCLXBASqaTD7BmY/WHXLMNj4Db"
			+ "o+yhAmSZs9egwcdvctImEM22KtOYop1XAd03a0ZZs35TDc7+ckqd9dt3b6m89ZPK+nNRu+uxd/bs+nculWWw+jVR//NJ43vrnjKnbge795Y7"
			+ "n3ioDF5zzTXXUUyB/zQ1xe5lcKYzMPS5lwXGOGxmZfNnlPwTnK6MH/3oR+Wuu+5qP83nxGXp+eUvf4m80dnALRV+0dQPX373u99tAAsUOQC4"
			+ "44472kcElrQf//jH5Vvf+lZ59NFHWzB8/gFsAPtNFLKduhwYvE1I9kqyAOv3ef2HwPIrEBJNMcME2MECvcIGMlQYCD6s0o+Xz+hnzam/+bhnf"
			+ "3uO27L1jXL73Q+XY5ccUbZu8/Ft3ceG61uDitK0OiXG9vvlivrHRfX7DC+vWV/OvmBF+e/XfbkM1qy9jlDGMsg1ENwLgGVJtjPCjLHJMkpGWz4"
			+ "YySBt+ABk3LJwww03NDCNeyNAB9my0FovI8nzkxlOfH6d7gc/+EELtkxX7r///jbrHn744XL99de3mSCzgaRo6RMA/face++9t+kyWyUNm9CZs"
			+ "T/72c/KLbfc0nwVeDbpBzKb0Qsof6KjKar/8DUBQR/s0Pr93flz6n/zcGJvGT1haXm6Hjq27ajv62bX10R1Vkx3+qqvR+bUI+/MaUP1J2rrr90"
			+ "tO6Zc9x/+ZfnspReUkZXPlwECKQU8RYk20BTGKUCLcvd4GKSfMQoemZLssayQBygZaYlA84lPfKI9Twiw/8aI5wM/ZCO7LXOWsl/84hftWYZd5"
			+ "H//1u/XB6lb209qCO6qurzkCMwGenxW73RFjyy/+eab2+wSILbHF7PcLwd9/etfbyc1/ezmk9MXuWTCIziwwbX+9LEjidgCV2mGZ3onVn/tYWS"
			+ "4XHP1VWXfHm8Peq9jButmPjRSf7nohVXlN8+uLH967T8pf/mf/105Y+/0svJP/lV5+t/+m97Pm8iQBINglVLKgKsN4K6NxQEGxihLmWKMA5Yos"
			+ "8B+AcC8IfZ6Jg9xvuiQmWZG+DzGxopXBaQl7fVNr7fZ42RkeaKLbWaGWeV3fPFbcgRGgEZHR9tM8VOzvsOVZNOS6/sDeTK3LJsldLHN6qDAJQm"
			+ "ndd/Ab6N1ma548LfVwbosDtSNekbdEyYGy7LjF5er//Xny0NPv1AnyEBZu/GNsvLZ58sVf3xB+bv//ZflX5x3btl4w/XlwT+6oBz+mY+UD9UVZ"
			+ "MBUpiQB4aQCaEqMc17mcURJsGKse/QKoxnMYcDhs87LehtyZo97SxQdslLQyADMhRde2ICW2Z4pVPTG8ftNLGCzB4gyHIDo/ZcZHKG9JGUX270"
			+ "d8PNOMt+Sq0+1kScg7GWH2WWW0pUA8FPRF58FIpUP7bo+FQ6M23vqr7EunFd17SrnrDi9/PurPlXu+ckz5fSTTyz/86/+vPzZn362LHjq8fLgC"
			+ "R8tW599pax48r6y+OLLyks3//Dg34cwngEcdq04XQFX1gFXJhsHRHcWucejj2FafUAGkFbWK3m3ZY2W3ZwEkgp4Jx6Z7sCgzwZNN9AB5leAgGK"
			+ "5o8vstqx51QJMM5Iuv+9rz7GnmFVmaH5pCJ9EMxOyp7FFMpghcGgAVzrXbAS6ws9u5auq4ElxuWB+/dG1rZvKRR87t9y0+LBy4QXnldlrVpWVV"
			+ "/5J2fbrO8oRN15fTrriU2X7Q8+VRy77bJn3vmN7S1aiHmEMUAQEsICSQYJBKQM4oDBYtinG4kyAloEAAxZAycisQCtAgNdPh0wnD4/sl9nsADQ"
			+ "7vE4xZjnDJ7Bko6Ff8M0UNA4jdAmuwNLvyE0+3YKMx5iWHAHqgq9fgVFwEhD88TVB6QYGzVB95T48s347c/tEueiPPlhe+8n3y6/OPrMMLDuun"
			+ "PnkU+XES/64rP4ff1seu+z8svS//LPyntv/+uB/epWAKKYI4ECSiYBROODa0sNZoHMOL2PQcxqwqv6AgF4lw0nGLBBsANGVd2bAI9c92cA1M8m"
			+ "nl1wnKEdk775kNb0CyW57kFf8TnGC5hTnJEWPd2jsAWxmhtnnXhAsVQJAl/v4pS8BQKskULnOfXBMu3BW/VOJsf1lw/ZNVf7isvymm8uSSy4s65"
			+ "55qrxy6ZfKnhl7yhn33lUWjZ5Snvv2d3szhFAColgr4xTA+I+jZEyfrHePB6iuOaEFjgo4RT9gOegHxSx/TlSWC6cvQbLEyGj8qrVepmd2kmE2"
			+ "oLE0OSF5tW7G+H0ToAuIfcZbY6AJuiCQRw4/klzGJQGQ0eBjc2ZZ7I5PzZH6D38lC3l4FfKnloz1+mtyH16/cFe33/EzTinD559Vfnf9N8srF36"
			+ "lHPMXnynn/+ym+qXnN8uj//ya9kKyfdmaIjUGaGUQJ2XUrro5yVLGyCJTm1OqPmBzSGEgg/TjdW2cPA+I2R8EgWOerr1ltnQBhJzsA9Z+8oBJhg"
			+ "AIouy3aas2ZjMHsAKZX5pzTT95fKM/M5dNAmL5s8cZ48vg4MEfosFHdzAJ8GSl6Etln31JIb/LO1hjN392/W+786fqWXjuOWX5Y5eXXQfGysr/9"
			+ "tdlcNNYOfbP/mnZuaT+DiVGAiKEQAa65wCnZKfNES3nQ5+A6BcAxguiFi85ke/etWXDA6Js5wRZZoulA7hb6n8aT5AEx/IIALOITKcne4Y1XzC9"
			+ "JvHNS3L1Ww7plzBmIvtcSyLy6cOn6LPJs0OJ/8Zt7BIn/Um21lH/IVdlG91sC4379JGJBu1I9WduXSl219clM087tbyy+oWy+/89XOaet6IMnrK"
			+ "8bKz4DR+oD68xJIIYrUap7HQt+kDidJS02VPH8TIInTZ01neAoAtACYJXH151mBk+kDIrgPTySy+32cguSaD6+mjkkye70UqCmkoNJ7r1CS699C"
			+ "loLH9sBL7lE4CZPa6N8Unlp9mWfjLYoqBzLWhoEwQ+KZGBJtfBxs9gzZu/oOJX/zp58/oyXp/WZ1zyj8qm+qT+xviesnjRYeWYxfXHpZuk+k+iT"
			+ "DghlMpqDnCWMyoj4oBgKfoYgTYlfZ64ZTqAPJzp96JNAMw267o9ybJlCTIbgGh58sB38cUXN3q6vUy87bbb2qeBnsTxCIIgmSF8wM8+zz/A1WdW"
			+ "CpIxPrHBkiYp+IZeoRed2SRgijGVf/gUPGjJpiP83dZYkq8XHJz144b6amXBvKPK/CMWlVkL6k/g1o3+xCWjZc5se/ZE+f9uXchM9f3tYwAAAAB"
			+ "JRU5ErkJggg==";

}
