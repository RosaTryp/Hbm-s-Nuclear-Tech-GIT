package com.hbm.inventory.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.hbm.inventory.container.ContainerTurretChekhov;
import com.hbm.lib.RefStrings;
import com.hbm.packet.AuxButtonPacket;
import com.hbm.packet.PacketDispatcher;
import com.hbm.tileentity.turret.TileEntityTurretChekhov;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GUITurretChekhov extends GuiInfoContainer {

	private static ResourceLocation texture = new ResourceLocation(RefStrings.MODID + ":textures/gui/weapon/gui_turret_base.png");
	private TileEntityTurretChekhov turret;
	int index;
	
	public GUITurretChekhov(InventoryPlayer invPlayer, TileEntityTurretChekhov tedf) {
		super(new ContainerTurretChekhov(invPlayer, tedf));
		turret = tedf;
		
		this.xSize = 176;
		this.ySize = 222;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);

		this.drawElectricityInfo(this, mouseX, mouseY, guiLeft + 152, guiTop + 45, 16, 52, turret.power, turret.getMaxPower());
	}

	protected void mouseClicked(int x, int y, int i) {
		super.mouseClicked(x, y, i);

		if(guiLeft + 115 <= x && guiLeft + 115 + 18 > x && guiTop + 26 < y && guiTop + 26 + 18 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(turret.xCoord, turret.yCoord, turret.zCoord, 0, 0));
			return;
		}

		if(guiLeft + 8 <= x && guiLeft + 8 + 10 > x && guiTop + 30 < y && guiTop + 30 + 10 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(turret.xCoord, turret.yCoord, turret.zCoord, 0, 1));
			return;
		}

		if(guiLeft + 22 <= x && guiLeft + 22 + 10 > x && guiTop + 30 < y && guiTop + 30 + 10 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(turret.xCoord, turret.yCoord, turret.zCoord, 0, 2));
			return;
		}

		if(guiLeft + 36 <= x && guiLeft + 36 + 10 > x && guiTop + 30 < y && guiTop + 30 + 10 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(turret.xCoord, turret.yCoord, turret.zCoord, 0, 3));
			return;
		}

		if(guiLeft + 50 <= x && guiLeft + 50 + 10 > x && guiTop + 30 < y && guiTop + 30 + 10 >= y) {

			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			PacketDispatcher.wrapper.sendToServer(new AuxButtonPacket(turret.xCoord, turret.yCoord, turret.zCoord, 0, 4));
			return;
		}
		
		int count = getCount();
		
		if(count > 0) {
			
			if(guiLeft + 7 <= x && guiLeft + 7 + 18 > x && guiTop + 80 < y && guiTop + 80 + 18 >= y) {
	
				index--;
				if(index < 0)
					index = count - 1;
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				return;
			}

			if(guiLeft + 43 <= x && guiLeft + 43 + 18 > x && guiTop + 80 < y && guiTop + 80 + 18 >= y) {
	
				index++;
				index %= count;
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
				return;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		String name = this.turret.hasCustomInventoryName() ? this.turret.getInventoryName() : I18n.format(this.turret.getInventoryName());
		
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
		
		List<String> names = turret.getWhitelist();
		
		String n = EnumChatFormatting.ITALIC + "None";
		
		if(names != null) {
			n = names.get(index);
		}
		
		double scale = 2;
		
		GL11.glScaled(1D / scale, 1D / scale, 1);
		this.fontRendererObj.drawString(n, (int)(12 * scale), (int)(51 * scale), 0x00ff00);
		GL11.glScaled(scale, scale, 1);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mX, int mY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if(guiLeft + 7 <= mX && guiLeft + 7 + 18 > mX && guiTop + 80 < mY && guiTop + 80 + 18 >=mY) {
			drawTexturedModalRect(guiLeft + 7, guiTop + 80, 176, 58, 18, 18);
		}
		if(guiLeft + 43 <= mX && guiLeft + 43 + 18 > mX && guiTop + 80 < mY && guiTop + 80 + 18 >=mY) {
			drawTexturedModalRect(guiLeft + 43, guiTop + 80, 194, 58, 18, 18);
		}
		if(guiLeft + 7 <= mX && guiLeft + 7 + 18 > mX && guiTop + 98 < mY && guiTop + 98 + 18 >=mY) {
			drawTexturedModalRect(guiLeft + 7, guiTop + 98, 176, 76, 18, 18);
		}
		if(guiLeft + 43 <= mX && guiLeft + 43 + 18 > mX && guiTop + 98 < mY && guiTop + 98 + 18 >=mY) {
			drawTexturedModalRect(guiLeft + 43, guiTop + 98, 194, 76, 18, 18);
		}
		
		int i = turret.getPowerScaled(53);
		drawTexturedModalRect(guiLeft + 152, guiTop + 97 - i, 194, 52 - i, 16, i);
		
		if(turret.isOn)
			drawTexturedModalRect(guiLeft + 115, guiTop + 26, 176, 40, 18, 18);
		
		if(turret.targetPlayers)
			drawTexturedModalRect(guiLeft + 8, guiTop + 30, 176, 0, 10, 10);
		
		if(turret.targetAnimals)
			drawTexturedModalRect(guiLeft + 22, guiTop + 30, 176, 10, 10, 10);
		
		if(turret.targetMobs)
			drawTexturedModalRect(guiLeft + 36, guiTop + 30, 176, 20, 10, 10);
		
		if(turret.targetMachines)
			drawTexturedModalRect(guiLeft + 50, guiTop + 30, 176, 30, 10, 10);
		
		int tallies = turret.stattrak;
		
		if(tallies >= 36) {
			
			drawTexturedModalRect(guiLeft + 77, guiTop + 50, 176, 120, 63, 6);
			
		} else {
			
			int steps = (int)Math.ceil(tallies / 5D);
			
			for(int s = 0; s < steps; s++) {
				
				int m = tallies % 5;
				
				if(s < steps - 1 || m == 0) {
					drawTexturedModalRect(guiLeft + 77 + 9 * s, guiTop + 50, 194, 94, 9, 6);
				} else {
					
					drawTexturedModalRect(guiLeft + 77 + 9 * s, guiTop + 50, 176, 94, m * 2, 6);
				}
			}
		}
	}
	
	private int getCount() {
		
		List<String> names = turret.getWhitelist();
		
		if(names == null)
			return 0;
		
		return names.size();
	}
}