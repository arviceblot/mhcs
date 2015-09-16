/**
 * 
 */
package com.mhcs.logan;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.mhcs.joshua.ImageURLs;

/**
 * Minimum configuration alert style.
 * @author Logan Sales
 * @version 0.2
 */
public class MinimumConfiguration extends Configuration {
	private final boolean isFPC;

	/**
	 * @param reqs
	 */
	public MinimumConfiguration(final int[] reqs) {
		super(reqs);
		this.isFPC = false;
	}
	
	public MinimumConfiguration(final int[] reqs, final boolean fpc) {
		super(reqs);
		this.isFPC = fpc;
	}

	/* (non-Javadoc)
	 * @see com.mhcs.logan.Configuration#meetsRequirements()
	 */
	@Override
	protected void meetsRequirements() {
		this.buildLayout();
		if (this.isFPC) {
			Window.alert("First possible configuration available!");
		}
	}

	/* (non-Javadoc)
	 * @see com.mhcs.logan.Configuration#buildLayout()
	 */
	@Override
	protected void buildLayout() {
		int plainCount = 0;
		this.size[0] = 3;
		this.size[1] = 4;
		for (Module module: this.modules) {
			if (module.getTypeInt() == Module.AIRLOCK) {
				this.layout[1][0] = module;
			} else if (module.getTypeInt() == Module.PLAIN) {
				plainCount++;
				this.layout[1][plainCount] = module;
			} else if (module.getTypeInt() == Module.POWER) {
				this.layout[0][1] = module;
			} else if (module.getTypeInt() == Module.CONTROL) {
				this.layout[0][2] = module;
			} else if (module.getTypeInt() == Module.CANTEEN) {
				this.layout[0][3] = module;
			} else if (module.getTypeInt() == Module.SANITATION) {
				this.layout[2][1] = module;
			} else if (module.getTypeInt() == Module.DORMITORY) {
				this.layout[2][2] = module;
			} else if (module.getTypeInt() == Module.FOOD) {
				this.layout[2][3] = module;
			}
		}
		
		Grid configGrid = new Grid(this.size[0], this.size[1]);
		for (int row = 0; row < this.size[0]; row++) {
			for (int col = 0; col < this.size[1]; col++) {
				if (layout[row][col] != null) {
					Image image = new Image(ImageURLs.moduleIdToURL(
							layout[row][col].getCode()));
					image.setPixelSize(
							ImageURLs.MAP_X_1TO1_SIZE,
							ImageURLs.MAP_Y_1TO1_SIZE);
					configGrid.setWidget(row, col, image);
							
				}
			}
		}
		
		this.mainPanel.add(configGrid);
	}
}
