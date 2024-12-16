// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class AircraftModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "aircraftmodel"), "main");
	private final ModelPart body;
	private final ModelPart wings;

	public AircraftModel(ModelPart root) {
		this.body = root.getChild("body");
		this.wings = root.getChild("wings");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-52, -10).addBox(-43.0F, -28.0F, -6.0F, 73.0F, 13.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create().texOffs(-36, -32).addBox(-12.0F, -18.0F, -38.0F, 13.0F, 3.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(-36, -32).addBox(-12.0F, -18.0F, 4.0F, 13.0F, 3.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(-5, 0).addBox(22.0F, -42.0F, -1.0F, 7.0F, 19.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(-13, -9).addBox(22.0F, -18.0F, -15.0F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(-13, -9).addBox(22.0F, -18.0F, 4.0F, 6.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wings.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}