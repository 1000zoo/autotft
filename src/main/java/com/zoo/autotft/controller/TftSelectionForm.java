package com.zoo.autotft.controller;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TftSelectionForm {

    private int level;
    private List<String> selectedChampions;
    private List<String> selectedSynergies;
}
