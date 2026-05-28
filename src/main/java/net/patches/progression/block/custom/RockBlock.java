package net.patches.progression.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.patches.progression.block.enums.RockVariants;


public class RockBlock extends Block {
    public static final MapCodec<RockBlock> CODEC = createCodec(RockBlock::new);
    public static final EnumProperty<RockVariants> ROCK_TYPE = EnumProperty.of("type", RockVariants.class);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    private static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 11.0, 1.0, 10.0);
    private static final VoxelShape MEDIUM_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 2.0, 11.0);
    private static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(4.0, 0.0, 5.0, 12.0, 2.0, 13.0);
    private static final VoxelShape EXTRA_LARGE_SHAPE = Block.createCuboidShape(4.0, 0.0, 5.0, 12.0, 2.0, 13.0);


    public RockBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(ROCK_TYPE, RockVariants.MEDIUM));
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {return CODEC;}

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState below = world.getBlockState(pos.down());
        boolean isValidGround = below.isOf(Blocks.STONE) || below.isOf(Blocks.DIRT) || below.isOf(Blocks.GRASS_BLOCK);

        if (!isValidGround) {
            return false;
        }

        BlockState existing = world.getBlockState(pos);
        return existing.isOf(this) || existing.isReplaceable();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        net.minecraft.util.math.random.Random random = net.minecraft.util.math.random.Random.create(pos.asLong());
        RockVariants variant = RockVariants.random(random);

        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(ROCK_TYPE, variant);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(ROCK_TYPE)) {
            case SMALL -> SMALL_SHAPE;
            case MEDIUM -> MEDIUM_SHAPE;
            case LARGE -> LARGE_SHAPE;
            case EXTRA_LARGE -> EXTRA_LARGE_SHAPE;
        };
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ROCK_TYPE);
    }

    public void cycleState(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.cycle(ROCK_TYPE), NOTIFY_LISTENERS);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!this.canPlaceAt(state, world, pos)) {
            world.breakBlock(pos, true);
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }
}
