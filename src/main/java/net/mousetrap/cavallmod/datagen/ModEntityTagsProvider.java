package net.mousetrap.cavallmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends EntityTypeTagsProvider {
    public ModEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CavallMod.MOD_ID, existingFileHelper);
        System.out.println(">>> ModEntityTagsProvider constructed!");
    }

    protected void addTags() {
        // Add tags for your entities here
        System.out.println(">>> ModEntityTagsProvider.addTags() running");

        this.tag(ModTags.FLAYFOLK_PREY)
                .add(ModEntities.FOGFOX.get());

        this.tag(ModTags.FOGFOX_PREDATORS)
                .add(ModEntities.FLAYFOLK.get())
                .add(EntityType.CAT);
    }

    @Override
    public String getName() {
        return "Mod Entity Tags";
    }
}

