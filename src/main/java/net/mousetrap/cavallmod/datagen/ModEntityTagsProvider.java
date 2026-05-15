package net.mousetrap.cavallmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.mousetrap.cavallmod.CavallMod;
import net.mousetrap.cavallmod.entity.ModEntities;
import net.mousetrap.cavallmod.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsProvider extends EntityTypeTagsProvider {
    public ModEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CavallMod.MOD_ID, existingFileHelper);
        System.out.println(">>> ModEntityTagsProvider constructed!");
    }

    //public ModEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
    //    super(output, lookupProvider);
    //    System.out.println(">>> ModEntityTagsProvider constructed!");
    //}

    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
        // Add tags for your entities here
        System.out.println(">>> ModEntityTagsProvider.addTags() running");
        System.out.println("Adding entity tags...");
        System.out.println("FLAYFOLK: " + ModEntities.FLAYFOLK.get());
        System.out.println("FOGFOX_PREDATORS tag: " + ModTags.FOGFOX_PREDATORS);

        this.tag(ModTags.FLAYFOLK_PREY)
                .add(ModEntities.FOGFOX.get());

        this.tag(ModTags.FOGFOX_PREDATORS)
                .add(ModEntities.FLAYFOLK.get());
    }

    @Override
    public String getName() {
        return "Mod Entity Tags";
    }
}

