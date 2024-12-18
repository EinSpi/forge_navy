// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class AircraftModel2<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "aircraftmodel2"), "main");
	private final ModelPart body;
	private final ModelPart wings;
	private final ModelPart wings2;
	private final ModelPart panel;
	private final ModelPart panel2;
	private final ModelPart vertical;
	private final ModelPart seat;
	private final ModelPart aim;
	private final ModelPart screw;
	private final ModelPart seat2;
	private final ModelPart bb_main;

	public AircraftModel2(ModelPart root) {
		this.body = root.getChild("body");
		this.wings = root.getChild("wings");
		this.wings2 = root.getChild("wings2");
		this.panel = root.getChild("panel");
		this.panel2 = root.getChild("panel2");
		this.vertical = root.getChild("vertical");
		this.seat = root.getChild("seat");
		this.aim = root.getChild("aim");
		this.screw = root.getChild("screw");
		this.seat2 = root.getChild("seat2");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 128).addBox(-9.5F, -37.0F, 19.0F, 19.0F, 19.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 84).addBox(-9.5F, -20.2F, -23.0F, 19.0F, 2.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(84, 182).addBox(-7.5F, -35.0F, -45.0F, 15.0F, 15.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(184, 26).addBox(-7.0F, -34.5F, -53.0F, 14.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 207).addBox(-6.5F, -34.0F, -58.0F, 13.0F, 13.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(208, 111).addBox(-6.0F, -33.5F, -63.0F, 12.0F, 12.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(74, 195).addBox(-2.0F, -29.5F, -103.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 145).addBox(-5.5F, -33.0F, -89.0F, 11.0F, 11.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(208, 128).addBox(-5.0F, -32.5F, -95.0F, 10.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(50, 195).addBox(-4.0F, -31.5F, -99.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(214, 213).addBox(-3.0F, -30.5F, -102.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(122, 102).addBox(-9.0F, -36.5F, -4.0F, 18.0F, 18.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(152, 145).addBox(-8.5F, -36.0F, -20.0F, 17.0F, 17.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(182, 48).addBox(-7.5F, -35.5F, -36.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition wings = partdefinition.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = wings.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(168, 203).addBox(-13.0F, -3.0F, -7.0F, 17.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-78.5441F, -24.9601F, 2.6958F, 0.0F, 0.48F, 0.0873F));

		PartDefinition cube_r2 = wings.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(168, 191).addBox(-13.0F, -3.0F, 0.0F, 16.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-80.3008F, -25.1138F, 2.4894F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r3 = wings.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(78, 128).addBox(-11.0F, -3.0F, 8.0F, 15.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-78.6065F, -24.9656F, 1.4451F, 0.0F, -0.3054F, 0.0873F));

		PartDefinition cube_r4 = wings.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 48).addBox(2.0F, -2.0F, -7.0F, 75.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-78.6654F, -24.9707F, 1.9066F, 0.0F, 0.2182F, 0.0873F));

		PartDefinition cube_r5 = wings.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(122, 84).addBox(0.0F, -2.0F, 7.0F, 71.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-79.0F, -25.0F, 2.0F, 0.0F, -0.0698F, 0.0873F));

		PartDefinition cube_base_r1 = wings.addOrReplaceChild("cube_base_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.9F, -7.0F, 71.0F, 3.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -3.0F, -7.0F, 71.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-79.0F, -25.0F, 2.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition wings2 = partdefinition.addOrReplaceChild("wings2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_base_r2 = wings2.addOrReplaceChild("cube_base_r2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-71.0F, -2.9F, -7.0F, 71.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 24).addBox(-71.0F, -3.0F, -7.0F, 71.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(79.0F, -25.0F, 2.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r6 = wings2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 207).addBox(-4.0F, -3.0F, -7.0F, 17.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(78.5441F, -24.9601F, 2.6958F, 0.0F, -0.48F, -0.0873F));

		PartDefinition cube_r7 = wings2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 195).addBox(-3.0F, -3.0F, 0.0F, 16.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(80.3008F, -25.1138F, 2.4894F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r8 = wings2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(208, 102).addBox(-4.0F, -3.0F, 8.0F, 15.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(78.6065F, -24.9656F, 1.4451F, 0.0F, 0.3054F, -0.0873F));

		PartDefinition cube_r9 = wings2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 66).addBox(-77.0F, -2.0F, -7.0F, 75.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(78.6654F, -24.9707F, 1.9066F, 0.0F, -0.2182F, -0.0873F));

		PartDefinition cube_r10 = wings2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(122, 93).addBox(-71.0F, -2.0F, 7.0F, 71.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(79.0F, -25.0F, 2.0F, 0.0F, 0.0698F, -0.0873F));

		PartDefinition panel = partdefinition.addOrReplaceChild("panel", CubeListBuilder.create().texOffs(0, 167).addBox(-34.0F, -27.0F, -84.0F, 29.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r11 = panel.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(78, 137).addBox(-3.0F, -2.0F, -7.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-38.5003F, -25.0F, -77.9552F, 0.0F, -1.0472F, 0.0F));

		PartDefinition cube_r12 = panel.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(218, 155).addBox(-3.0F, -2.0F, -6.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0574F, -25.0F, -75.4812F, 0.0F, -0.2618F, 0.0F));

		PartDefinition cube_r13 = panel.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(218, 150).addBox(-3.0F, -2.0F, -6.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-33.5133F, -25.0F, -74.3353F, 0.0F, 0.3054F, 0.0F));

		PartDefinition cube_r14 = panel.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(24, 217).addBox(-5.0F, -2.0F, -6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.4938F, -25.0F, -76.9486F, 0.0F, 0.9599F, 0.0F));

		PartDefinition cube_r15 = panel.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(152, 178).addBox(0.0F, -2.0F, -11.0F, 31.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-34.0F, -25.0F, -74.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r16 = panel.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(184, 12).addBox(0.0F, -2.0F, -10.0F, 29.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.6083F, -25.0F, -74.0973F, 0.0F, 0.1396F, 0.0F));

		PartDefinition panel2 = partdefinition.addOrReplaceChild("panel2", CubeListBuilder.create().texOffs(184, 0).addBox(5.0F, -27.0F, -84.0F, 29.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r17 = panel2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(96, 137).addBox(-2.0F, -2.0F, -7.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.5003F, -25.0F, -77.9552F, 0.0F, 1.0472F, 0.0F));

		PartDefinition cube_r18 = panel2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(218, 165).addBox(-2.0F, -2.0F, -6.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(36.0574F, -25.0F, -75.4812F, 0.0F, 0.2618F, 0.0F));

		PartDefinition cube_r19 = panel2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(218, 160).addBox(-2.0F, -2.0F, -6.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(33.5133F, -25.0F, -74.3353F, 0.0F, -0.3054F, 0.0F));

		PartDefinition cube_r20 = panel2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(218, 144).addBox(1.0F, -2.0F, -6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(29.4938F, -25.0F, -76.9486F, 0.0F, -0.9599F, 0.0F));

		PartDefinition cube_r21 = panel2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 182).addBox(-31.0F, -2.0F, -11.0F, 31.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(34.0F, -25.0F, -74.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r22 = panel2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(184, 19).addBox(-29.0F, -2.0F, -10.0F, 29.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(32.6083F, -25.0F, -74.0973F, 0.0F, -0.1396F, 0.0F));

		PartDefinition vertical = partdefinition.addOrReplaceChild("vertical", CubeListBuilder.create().texOffs(134, 191).addBox(-1.55F, -48.0F, -90.0F, 1.0F, 18.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(134, 191).mirror().addBox(0.55F, -48.0F, -90.0F, 1.0F, 18.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r23 = vertical.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(6, 217).addBox(-1.5F, -5.0F, 3.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -51.5877F, -89.1864F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r24 = vertical.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(216, 203).addBox(-1.5F, -5.0F, 2.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -47.7588F, -89.2221F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r25 = vertical.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(194, 213).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -48.4318F, -85.8901F, -0.829F, 0.0F, 0.0F));

		PartDefinition cube_r26 = vertical.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(108, 207).addBox(-1.5F, -5.0F, -8.0F, 3.0F, 23.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -45.7067F, -83.2454F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r27 = vertical.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(84, 207).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 20.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -43.985F, -81.7341F, 0.6109F, 0.0F, 0.0F));

		PartDefinition seat = partdefinition.addOrReplaceChild("seat", CubeListBuilder.create().texOffs(102, 143).addBox(-2.9581F, -42.9088F, 18.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r28 = seat.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 179).addBox(2.0F, 1.0F, -8.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6008F, -32.6352F, -30.5156F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r29 = seat.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(118, 137).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -37.0F, 19.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r30 = seat.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(90, 143).addBox(2.0F, 1.0F, -8.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6108F, -36.8785F, -8.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r31 = seat.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(114, 137).addBox(0.0F, -4.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2954F, -36.6959F, -31.5156F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r32 = seat.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(218, 191).addBox(0.0F, -8.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 217).addBox(0.0F, -8.0F, 18.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -37.0F, -9.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r33 = seat.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(78, 143).addBox(2.0F, 1.0F, -8.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.6108F, -36.8785F, 11.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r34 = seat.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(222, 191).addBox(0.0F, 1.0F, -8.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3533F, -34.9789F, 18.5893F, -1.7473F, 0.2174F, 0.1629F));

		PartDefinition cube_r35 = seat.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(128, 207).addBox(0.4786F, -24.2103F, 0.2212F, 1.0F, 23.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9157F, -40.7152F, -33.7952F, -1.4054F, -0.1227F, 0.1904F));

		PartDefinition cube_r36 = seat.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(20, 217).addBox(0.0F, 1.0F, -8.0F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -37.0F, 10.0F, -1.5708F, 0.0F, 0.1745F));

		PartDefinition aim = partdefinition.addOrReplaceChild("aim", CubeListBuilder.create().texOffs(8, 179).addBox(-0.5F, -39.0F, 23.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition screw = partdefinition.addOrReplaceChild("screw", CubeListBuilder.create().texOffs(168, 213).addBox(-4.0F, -32.0F, 39.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(134, 182).addBox(-3.0F, -31.0F, 44.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(136, 183).addBox(-2.0F, -30.0F, 47.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(138, 184).addBox(-1.0F, -29.0F, 50.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r37 = screw.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(40, 217).addBox(1.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 217).addBox(-2.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 217).addBox(-1.0F, 3.0F, 0.0F, 2.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 46.0F, 0.0F, 0.3491F, -2.0944F));

		PartDefinition cube_r38 = screw.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(40, 217).addBox(1.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 217).addBox(-2.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 217).addBox(-1.0F, 3.0F, 0.0F, 2.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 46.0F, 0.0F, 0.3491F, 2.0944F));

		PartDefinition cube_r39 = screw.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(40, 217).addBox(1.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 217).addBox(-2.0F, 6.0F, 0.0F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 217).addBox(-1.0F, 3.0F, 0.0F, 2.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -28.0F, 46.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition seat2 = partdefinition.addOrReplaceChild("seat2", CubeListBuilder.create().texOffs(102, 143).mirror().addBox(-0.0419F, -42.9088F, 18.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r40 = seat2.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 179).mirror().addBox(-5.0F, 1.0F, -8.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.6008F, -32.6352F, -30.5156F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r41 = seat2.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(118, 137).mirror().addBox(-1.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, -37.0F, 19.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r42 = seat2.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(90, 143).mirror().addBox(-7.0F, 1.0F, -8.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.6108F, -36.8785F, -8.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r43 = seat2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(114, 137).mirror().addBox(-1.0F, -4.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.2954F, -36.6959F, -31.5156F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r44 = seat2.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(218, 191).mirror().addBox(-1.0F, -8.0F, -1.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 217).mirror().addBox(-1.0F, -8.0F, 18.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -37.0F, -9.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r45 = seat2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(78, 143).mirror().addBox(-7.0F, 1.0F, -8.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.6108F, -36.8785F, 11.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r46 = seat2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(222, 191).mirror().addBox(-1.0F, 1.0F, -8.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.3533F, -34.9789F, 18.5893F, -1.7473F, -0.2174F, -0.1629F));

		PartDefinition cube_r47 = seat2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(128, 207).mirror().addBox(-1.4786F, -24.2103F, 0.2212F, 1.0F, 23.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.9157F, -40.7152F, -33.7952F, -1.4054F, 0.1227F, -0.1904F));

		PartDefinition cube_r48 = seat2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(20, 217).mirror().addBox(-1.0F, 1.0F, -8.0F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, -37.0F, 10.0F, -1.5708F, 0.0F, -0.1745F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(135, 182).addBox(-2.0F, -30.0F, 47.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wings.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		wings2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		panel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		panel2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		vertical.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		seat.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		aim.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		screw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		seat2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}