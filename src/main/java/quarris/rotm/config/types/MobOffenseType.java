package quarris.rotm.config.types;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class MobOffenseType {

    public ResourceLocation potion;
    public int level;
    public int duration;
    public float health;
    public float chance;
    public ResourceLocation sound;
    public List<Integer> dimensions;
    public boolean blockDimensions;
    public String damageType;

    public MobOffenseType(ResourceLocation potion, int level, int duration, float health, float chance, ResourceLocation sound, List<Integer> dimensions, boolean blockDimensions, String damageType) {
        this.potion = potion;
        this.level = level;
        this.duration = duration;
        this.health = health;
        this.chance = chance;
        this.sound = sound;
        this.dimensions = dimensions;
        this.blockDimensions = blockDimensions;
        this.damageType = damageType;
    }

    public boolean canApplyToEntity(EntityLivingBase entity) {
        // Check the health, chance and dimension
        return (this.health == 1.0f || entity.getHealth() / entity.getMaxHealth() <= this.health) &&
                (this.chance == 1.0f || entity.world.rand.nextFloat() <= this.chance) &&
                this.blockDimensions != this.dimensions.contains(entity.dimension);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MobAttackType{");
        sb.append("potion=").append(potion);
        sb.append(", level=").append(level);
        sb.append(", duration=").append(duration);
        sb.append(", health=").append(health);
        sb.append(", chance=").append(chance);
        sb.append(", sound=").append(sound);
        sb.append(", dimensions=").append(dimensions);
        sb.append(", blockDimensions=").append(blockDimensions);
        sb.append(", damageType=").append(damageType);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        public ResourceLocation potion;
        public int level;
        public int duration;
        public float health;
        public float chance;
        public ResourceLocation sound;
        public List<Integer> dimensions;
        public boolean blockDimensions;
        public String damageType;

        Builder() {
            this.dimensions = new ArrayList<>();
        }

        public Builder potion(ResourceLocation potion) {
            this.potion = potion;
            return this;
        }

        public Builder level(int level) {
            this.level = level;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration * 20;
            return this;
        }

        public Builder health(float health) {
            this.health = health / 100f;
            return this;
        }

        public Builder chance(float chance) {
            this.chance = chance / 100f;
            return this;
        }

        public Builder sound(ResourceLocation sound) {
            this.sound = sound;
            return this;
        }

        public Builder dimension(int dimId) {
            this.dimensions.add(dimId);
            return this;
        }

        public Builder blockDimensions(boolean blockDimensions) {
            this.blockDimensions = blockDimensions;
            return this;
        }

        public Builder damageType(String damageType) {
            this.damageType = damageType;
            return this;
        }

        public MobOffenseType build() {
            return new MobOffenseType(this.potion, this.level, this.duration, this.health, this.chance, this.sound, this.dimensions, this.blockDimensions, this.damageType);
        }
    }
}
