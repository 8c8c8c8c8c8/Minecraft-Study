package zz.dbrvkf.minecraft_study.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import zz.dbrvkf.minecraft_study.entity.custom.RhinoEntity;

public class RhinoAttackGoal extends MeleeAttackGoal {
    private final RhinoEntity entity;
    private int attackDelay = 40;
    private int ticksUntilNextAttack = 40;
    private boolean shouldCountTillNextAttack = false;

    public RhinoAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.entity = (RhinoEntity) pMob;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 40;
        ticksUntilNextAttack = 40;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack)
            ticksUntilNextAttack = Math.max(ticksUntilNextAttack - 1, 0);
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    protected void resetAttackCooldown() {
        ticksUntilNextAttack = adjustedTickDelay(attackDelay * 2);
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        mob.swing(InteractionHand.MAIN_HAND);
        mob.doHurtTarget(pEnemy);
    }

    @Override
    protected boolean isTimeToAttack() {
        return ticksUntilNextAttack <= 0;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;

            if (isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }
            if (this.isTimeToAttack()) {
                mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
            return;
        }
        this.resetAttackCooldown();
        shouldCountTillNextAttack = false;
        entity.setAttacking(false);
        entity.resetAttackTimeout();
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= getAttackReachSqr(pEnemy);
    }

    private boolean isTimeToStartAttackAnimation() {
        return ticksUntilNextAttack <= attackDelay;
    }
}
