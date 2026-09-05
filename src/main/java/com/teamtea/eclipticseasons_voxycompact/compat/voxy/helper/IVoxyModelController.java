package com.teamtea.eclipticseasons_voxycompact.compat.voxy.helper;

import com.teamtea.eclipticseasons.client.lod.SeasonalModelEntry;
import org.jetbrains.annotations.Nullable;

public interface IVoxyModelController {
    boolean isSnowyBlock();

    void setSnowyBlock(boolean snowyBlock);

    @Nullable
    SeasonalModelEntry getSeasonalModel();

    void setSeasonalModel(@Nullable SeasonalModelEntry seasonalModel);
}
