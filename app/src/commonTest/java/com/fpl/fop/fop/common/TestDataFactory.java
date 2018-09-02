package com.fpl.fop.fop.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fpl.fop.fop.data.model.response.BfwTeam;

/**
 * Factory class that makes instances of data models with random field values. The aim of this class
 * is to help setting up test fixtures.
 */
public class TestDataFactory {

    private static final Random random = new Random();

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Pokemon makePokemon(String id) {
        Pokemon pokemon = new Pokemon();
        pokemon.id = id;
        pokemon.name = randomUuid() + id;
        pokemon.stats = makeStatisticList(3);
        pokemon.sprites = makeSprites();
        return pokemon;
    }

    public static List<String> makePokemonNamesList(int count) {
        List<String> pokemonList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            pokemonList.add(makePokemon(String.valueOf(i)).name);
        }
        return pokemonList;
    }

    public static List<String> makePokemonNameList(List<BfwTeam> pokemonList) {
        List<String> names = new ArrayList<>();
        for (BfwTeam pokemon : pokemonList) {
            names.add(pokemon.name);
        }
        return names;
    }

    public static Statistic makeStatistic() {
        Statistic statistic = new Statistic();
        statistic.baseStat = random.nextInt();
        statistic.stat = makeNamedResource(randomUuid());
        return statistic;
    }

    public static List<Statistic> makeStatisticList(int count) {
        List<Statistic> statisticList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            statisticList.add(makeStatistic());
        }
        return statisticList;
    }

    public static Sprites makeSprites() {
        Sprites sprites = new Sprites();
        sprites.frontDefault = randomUuid();
        return sprites;
    }

    public static BfwTeam makeNamedResource(String unique) {
        BfwTeam bfwTeam = new BfwTeam();
        bfwTeam.name = randomUuid() + unique;
        bfwTeam.url = randomUuid();
        return bfwTeam;
    }

    public static List<BfwTeam> makeNamedResourceList(int count) {
        List<BfwTeam> bfwTeamList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            bfwTeamList.add(makeNamedResource(String.valueOf(i)));
        }
        return bfwTeamList;
    }
}
