package io.github.eat_ram.cntrafficsymbols.core.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;

public class SquareQuarterDefaulted3Block extends Block
implements ManagedWaterloggable {
    public static final IntProperty NORTHWEST =
    IntProperty.of("northwest", 0, 2);
    public static final IntProperty NORTHEAST =
    IntProperty.of("northeast", 0, 2);
    public static final IntProperty SOUTHWEST =
    IntProperty.of("southwest", 0, 2);
    public static final IntProperty SOUTHEAST =
    IntProperty.of("southeast", 0, 2);
    private Item itm0;
    private Item itm1;
    private Item itm2;

    public SquareQuarterDefaulted3Block(
        Item itm0, Item itm1, Item itm2, Settings settings
    ) {
        super(settings);
        this.itm0 = itm0;
        this.itm1 = itm1;
        this.itm2 = itm2;
        BlockState st = this.getDefaultState().with(NORTHWEST, 0)
                        .with(NORTHEAST, 0).with(SOUTHWEST, 0)
                        .with(SOUTHEAST, 0);
        if (this.getWaterloggedProperty()) {
            st = st.with(WATERLOGGED, false);
        }
        this.setDefaultState(st);
    }

    public SquareQuarterDefaulted3Block(Settings settings) {
        super(settings);
        BlockState st = this.getDefaultState().with(NORTHWEST, 0)
                        .with(NORTHEAST, 0).with(SOUTHWEST, 0)
                        .with(SOUTHEAST, 0);
        if (this.getWaterloggedProperty()) {
            st = st.with(WATERLOGGED, false);
        }
        this.setDefaultState(st);
    }

    @Override
    public ItemStack
    getPickStack(WorldView world, BlockPos pos, BlockState state) {
        byte s1 = 0;
        byte s2 = 0;
        switch (state.get(NORTHWEST).intValue()) {
            case 1:
                s1++;
                break;
            case 2:
                s2++;
                break;
        }
        switch (state.get(NORTHEAST).intValue()) {
            case 1:
                s1++;
                break;
            case 2:
                s2++;
                break;
        }
        switch (state.get(SOUTHWEST).intValue()) {
            case 1:
                s1++;
                break;
            case 2:
                s2++;
                break;
        }
        switch (state.get(SOUTHEAST).intValue()) {
            case 1:
                s1++;
                break;
            case 2:
                s2++;
                break;
        }
        if (s1 == (byte)0) {
            return new ItemStack((s2 == (byte)0) ? this.itm0 : this.itm2);
        }
        if (s2 == (byte)0) {
            return new ItemStack(this.itm1);
        }
        return new ItemStack((Math.random() < .5) ? this.itm1 : this.itm2);
    }

    @Override
    protected void
    appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST);
        if (this.getWaterloggedProperty()) {
            builder.add(WATERLOGGED);
        }
    }

    public boolean shouldHardcodedDrop(
        World world, PlayerEntity player, BlockPos pos, BlockState state,
        @Nullable BlockEntity blockEntity, ItemStack tool
    ) {
        return true;
    }

    @Override
    public void afterBreak(
        World world, PlayerEntity player, BlockPos pos,
        BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool
    ) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if ((world instanceof ServerWorld) && this.shouldHardcodedDrop(
            world, player, pos, state, blockEntity, tool
        )) {
            byte s1 = 0;
            byte s2 = 0;
            switch (state.get(NORTHWEST).intValue()) {
                case 1:
                    s1++;
                    break;
                case 2:
                    s2++;
                    break;
            }
            switch (state.get(NORTHEAST).intValue()) {
                case 1:
                    s1++;
                    break;
                case 2:
                    s2++;
                    break;
            }
            switch (state.get(SOUTHWEST).intValue()) {
                case 1:
                    s1++;
                    break;
                case 2:
                    s2++;
                    break;
            }
            switch (state.get(SOUTHEAST).intValue()) {
                case 1:
                    s1++;
                    break;
                case 2:
                    s2++;
                    break;
            }
            if (s1 == (byte)0 && s2 == (byte)0) {
                if (this.itm0 != null) {
                    ItemScatterer.spawn(
                        world, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(this.itm0)
                    );
                }
            } else {
                if (this.itm1 != null) {
                    ItemScatterer.spawn(
                        world, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(this.itm1, s1)
                    );
                }
                if (this.itm2 != null) {
                    ItemScatterer.spawn(
                        world, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(this.itm2, s2)
                    );
                }
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState st = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (st.isOf(this)) {
            byte repl = 1;
            if (ctx.getStack().isOf(this.itm2)) {
                repl++;
            } else if (!ctx.getStack().isOf(this.itm1)) {
                throw new IllegalStateException(
                    "Unexpected item " + ctx.getStack().getItem()
                );
            }
            return st.with(readyToReplace(st, ctx), (int) repl);
        }
        if (ctx.getStack().isOf(this.itm0)) {
            if (this.getWaterloggedProperty()) {
                return this.getDefaultState().with(
                    WATERLOGGED, ctx.getWorld().getFluidState(
                        ctx.getBlockPos()
                    ).getFluid() == WATER
                );
            }
            return this.getDefaultState();
        }
        byte repl = 1;
        if (ctx.getStack().isOf(this.itm2)) {
            repl++;
        } else if (!ctx.getStack().isOf(this.itm1)) {
            throw new IllegalStateException(
                "Unexpected item " + ctx.getStack().getItem()
            );
        }
        BlockState sst = this.getDefaultState().with(
            readyToReplace(null, ctx), (int)repl
        );
        if (this.getWaterloggedProperty()) {
            return sst.with(WATERLOGGED, ctx.getWorld().getFluidState(
                ctx.getBlockPos()
            ).getFluid() == WATER);
        }
        return sst;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        return (ctx.getStack().isOf(this.itm1) ||
                ctx.getStack().isOf(this.itm2)) &&
               readyToReplace(state, ctx) != null;
    }

    public @Nullable
    static IntProperty
    readyToReplace(@Nullable BlockState state, ItemPlacementContext ctx) {
        int nw = (state != null) ? state.get(NORTHWEST).intValue() : 0;
        int ne = (state != null) ? state.get(NORTHEAST).intValue() : 0;
        int sw = (state != null) ? state.get(SOUTHWEST).intValue() : 0;
        int se = (state != null) ? state.get(SOUTHEAST).intValue() : 0;
        if (state != null && nw == 0 && ne == 0 && sw == 0 && se == 0) {
            return null;
        }
        double rx = ctx.getHitPos().x - ctx.getBlockPos().getX();
        double rz = ctx.getHitPos().z - ctx.getBlockPos().getZ();
        switch (ctx.getSide()) {
            case DOWN:
            case UP: {
                if (rx < .5) {
                    if (rz < .5) {
                        return (nw == 0) ? NORTHWEST : null;
                    }
                    return (sw == 0) ? SOUTHWEST : null;
                }
                if (rz < .5) {
                    return (ne == 0) ? NORTHEAST : null;
                }
                return (se == 0) ? SOUTHEAST : null;
            }
            case NORTH: {
                if (rx < .5) {
                    if (rz < .4375) {
                        return null;
                    }
                    if (sw == 0) {
                        return SOUTHWEST;
                    }
                    return (nw == 0) ? NORTHWEST : null;
                }
                if (se == 0) {
                    return SOUTHEAST;
                }
                return (ne == 0) ? NORTHEAST : null;
            }
            case SOUTH: {
                if (rx < .5) {
                    if (nw == 0) {
                        return NORTHWEST;
                    }
                    return (sw == 0) ? SOUTHWEST : null;
                }
                if (rz > .5625) {
                    return null;
                }
                if (ne == 0) {
                    return NORTHEAST;
                }
                return (se == 0) ? SOUTHEAST : null;
            }
            case WEST: {
                if (rz < .5) {
                    if (rx < .4375) {
                        return null;
                    }
                    if (ne == 0) {
                        return NORTHEAST;
                    }
                    return (nw == 0) ? NORTHWEST : null;
                }
                if (se == 0) {
                    return SOUTHEAST;
                }
                return (sw == 0) ? SOUTHWEST : null;
            }
            case EAST: {
                if (rz < .5) {
                    if (nw == 0) {
                        return NORTHWEST;
                    }
                    return (ne == 0) ? NORTHEAST : null;
                }
                if (rx > .5625) {
                    return null;
                }
                if (sw == 0) {
                    return SOUTHWEST;
                }
                return (se == 0) ? SOUTHEAST : null;
            }
            default: return null;
        }
    }

    @Override
    public BlockState rotate(BlockState st, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_90: return st.with(NORTHWEST, st.get(SOUTHWEST))
                                        .with(NORTHEAST, st.get(NORTHWEST))
                                        .with(SOUTHEAST, st.get(NORTHEAST))
                                        .with(SOUTHWEST, st.get(SOUTHEAST));
            case CLOCKWISE_180: return st.with(NORTHWEST, st.get(SOUTHEAST))
                                         .with(NORTHEAST, st.get(SOUTHWEST))
                                         .with(SOUTHEAST, st.get(NORTHWEST))
                                         .with(SOUTHWEST, st.get(NORTHEAST));
            case COUNTERCLOCKWISE_90:
                return st.with(NORTHWEST, st.get(NORTHEAST))
                         .with(NORTHEAST, st.get(SOUTHEAST))
                         .with(SOUTHEAST, st.get(SOUTHWEST))
                         .with(SOUTHWEST, st.get(NORTHWEST));
            default: return st;
        }
    }

    @Override
    public BlockState mirror(BlockState st, BlockMirror mirror) {
        switch (mirror) {
            case FRONT_BACK: return st.with(NORTHWEST, st.get(NORTHEAST))
                                      .with(NORTHEAST, st.get(NORTHWEST))
                                      .with(SOUTHWEST, st.get(SOUTHEAST))
                                      .with(SOUTHEAST, st.get(SOUTHWEST));
            case LEFT_RIGHT: return st.with(NORTHWEST, st.get(SOUTHWEST))
                                      .with(NORTHEAST, st.get(SOUTHEAST))
                                      .with(SOUTHWEST, st.get(NORTHWEST))
                                      .with(SOUTHEAST, st.get(NORTHEAST));
            default: return st;
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(
        BlockState state, Direction direction, BlockState neighborState,
        WorldAccess world, BlockPos pos, BlockPos neighborPos
    ) {
        if (this.getWaterloggedProperty() &&
            state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, WATER, WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(
            state, direction, neighborState, world, pos, neighborPos
        );
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (this.getWaterloggedProperty() &&
                state.get(WATERLOGGED).booleanValue()) ?
               WATER.getStill(false) : super.getFluidState(state);
    }

    public @Nullable Item setItm0(@Nullable Item v) {
        Item r = this.itm0;
        if (r == null) {
            this.itm0 = v;
        }
        return v;
    }

    public @Nullable Item setItm1(@Nullable Item v) {
        Item r = this.itm1;
        if (r == null) {
            this.itm1 = v;
        }
        return v;
    }

    public @Nullable Item setItm2(@Nullable Item v) {
        Item r = this.itm2;
        if (r == null) {
            this.itm2 = v;
        }
        return v;
    }
}
