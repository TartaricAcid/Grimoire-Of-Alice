package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelSuwakoHat extends ModelBiped {
    public ModelRenderer shape15;
    public ModelRenderer shape15_1;
    public ModelRenderer shape15_2;
    public ModelRenderer shape15_3;
    public ModelRenderer shape15_4;
    public ModelRenderer shape15_5;
    public ModelRenderer shape15_6;

    public ModelSuwakoHat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape15_6 = new ModelRenderer(this, 32, 32);
        this.shape15_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_6.addBox(-4.0F, -11.0F, -7.0F, 8, 3, 0, 0.0F);
        this.shape15 = new ModelRenderer(this, 0, 0);
        this.shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15.addBox(-4.0F, -13.0F, -4.0F, 8, 5, 8, 0.0F);
        this.shape15_1 = new ModelRenderer(this, 19, 0);
        this.shape15_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_1.addBox(-7.0F, -8.0F, -7.0F, 14, 0, 14, 0.0F);
        this.shape15_4 = new ModelRenderer(this, 0, 32);
        this.shape15_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_4.addBox(-7.0F, -8.0F, 6.0F, 14, 1, 1, 0.0F);
        this.shape15_2 = new ModelRenderer(this, 0, 14);
        this.shape15_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_2.addBox(-7.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
        this.shape15_5 = new ModelRenderer(this, 0, 35);
        this.shape15_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_5.addBox(6.0F, -8.0F, -7.0F, 1, 1, 14, 0.0F);
        this.shape15_3 = new ModelRenderer(this, 0, 32);
        this.shape15_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_3.addBox(-7.0F, -8.0F, -7.0F, 14, 1, 1, 0.0F);
        bipedHeadwear.addChild(shape15);
        this.shape15.addChild(this.shape15_4);
        this.shape15.addChild(this.shape15_6);
        this.shape15.addChild(this.shape15_2);
        this.shape15.addChild(this.shape15_1);
        this.shape15.addChild(this.shape15_5);
        this.shape15.addChild(this.shape15_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bipedHeadwear.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
