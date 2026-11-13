package com.template.templatemod.entity.client.Giraffe;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.template.templatemod.entity.client.Giraffe.GiraffeRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GiraffeSaddleModel extends EntityModel<GiraffeRenderState> {
	private final ModelPart Giraffe;
	private final ModelPart upper_body_group;
	private final ModelPart Neck;
	private final ModelPart Head;
	private final ModelPart lower_body_group;
	private final ModelPart l_leg;
	private final ModelPart l_leg2;
	private final ModelPart r_leg;
	private final ModelPart r_leg2;

	public GiraffeSaddleModel(ModelPart root) {
        super(root);
        this.Giraffe = root.getChild("Giraffe");
		this.upper_body_group = this.Giraffe.getChild("upper_body_group");
		this.Neck = this.upper_body_group.getChild("Neck");
		this.Head = this.Neck.getChild("Head");
		this.lower_body_group = this.Giraffe.getChild("lower_body_group");
		this.l_leg = this.lower_body_group.getChild("l_leg");
		this.l_leg2 = this.lower_body_group.getChild("l_leg2");
		this.r_leg = this.lower_body_group.getChild("r_leg");
		this.r_leg2 = this.lower_body_group.getChild("r_leg2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Giraffe = partdefinition.addOrReplaceChild("Giraffe", CubeListBuilder.create(), PartPose.offset(-7.0F, -17.0F, -11.0F));

		PartDefinition upper_body_group = Giraffe.addOrReplaceChild("upper_body_group", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.0F, -12.0F, 16.0F, 0.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(32, 49).addBox(-8.0F, -4.0F, -12.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.3F))
		.texOffs(32, 49).addBox(4.0F, -4.0F, -12.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.3F))
		.texOffs(0, 49).addBox(-8.0F, -4.0F, 9.0F, 16.0F, 7.0F, 0.0F, new CubeDeformation(0.3F))
		.texOffs(42, 21).addBox(8.0F, -4.0F, -12.0F, 0.0F, 7.0F, 21.0F, new CubeDeformation(0.3F))
		.texOffs(0, 21).addBox(-8.0F, -4.0F, -12.0F, 0.0F, 7.0F, 21.0F, new CubeDeformation(0.3F)), PartPose.offset(7.0F, 23.0F, 15.0F));

		PartDefinition Neck = upper_body_group.addOrReplaceChild("Neck", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, -10.0F));

		PartDefinition Head = Neck.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(7.0F, -17.0F, -5.0F));

		PartDefinition lower_body_group = Giraffe.addOrReplaceChild("lower_body_group", CubeListBuilder.create(), PartPose.offset(7.0F, 21.0F, 11.0F));

		PartDefinition l_leg = lower_body_group.addOrReplaceChild("l_leg", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, -7.0F));

		PartDefinition l_leg2 = lower_body_group.addOrReplaceChild("l_leg2", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, 12.0F));

		PartDefinition r_leg = lower_body_group.addOrReplaceChild("r_leg", CubeListBuilder.create(), PartPose.offset(7.0F, 5.0F, -7.0F));

		PartDefinition r_leg2 = lower_body_group.addOrReplaceChild("r_leg2", CubeListBuilder.create(), PartPose.offset(-7.0F, 4.0F, 12.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(GiraffeRenderState state) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
	}
}