package com.android.consumermoviecatalogue.utils;

import java.util.ArrayList;

public class GenreChecks {


    public static String MovieGenre(int genre) {
        String genreInString = null;
        String currentLanguage = LanguageFormater.checkCurrentLanguage();
        if (genre == 28) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Action";
            } else {
                genreInString = "Aksi";
            }
        }
        if (genre == 12) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Adventure";
            } else {
                genreInString = "Petualangan";
            }
        }
        if (genre == 16) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Animation";
            } else {
                genreInString = "Animasi";
            }
        }
        if (genre == 35) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Comedy";
            } else {
                genreInString = "Komedi";
            }
        }
        if (genre == 80) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Crime";
            } else {
                genreInString = "Kejahatan";
            }
        }
        if (genre == 99) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Documentary";
            } else {
                genreInString = "Dokumentasi";
            }
        }
        if (genre == 18) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Drama";
            } else {
                genreInString = "Drama";
            }
        }
        if (genre == 10751) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Family";
            } else {
                genreInString = "Keluarga";
            }
        }
        if (genre == 14) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Fantasy";
            } else {
                genreInString = "Fantasi";
            }
        }
        if (genre == 36) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "History";
            } else {
                genreInString = "Sejarah";
            }
        }
        if (genre == 27) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Horror";
            } else {
                genreInString = "Hantu";
            }
        }
        if (genre == 10402) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Music";
            } else {
                genreInString = "Musik";
            }
        }
        if (genre == 9468) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Mystery";
            } else {
                genreInString = "Ilmiah";
            }
        }
        if (genre == 878) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Science Fiction";
            } else {
                genreInString = "Fiksi Ilmiah";
            }
        }
        if (genre == 10770) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "TV Movie";
            } else {
                genreInString = "Tayangan TV";
            }
        }
        if (genre == 53) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Thriller";
            } else {
                genreInString = "Menegangkan";
            }
        }
        if (genre == 10752) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "War";
            } else {
                genreInString = "Perang";
            }
        }
        if (genre == 37) {
            if (currentLanguage.equalsIgnoreCase("en-US")) {
                genreInString = "Western";
            } else {
                genreInString = "Budaya barat";
            }
        }
        return genreInString;
    }

    public static ArrayList<String> TvShowGenre(ArrayList<Integer> genre) {
        ArrayList<String> genreInString = new ArrayList<>();
        String currentLanguage = LanguageFormater.checkCurrentLanguage();
        for (int i = 0; i < genre.size(); i++) {
            if (genre.get(i) == 10759) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Action and Adventure");
                } else {
                    genreInString.add("Aksi dan Petualangan");
                }
            }
            if (genre.get(i) == 16) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Animation");
                } else {
                    genreInString.add("Animasi");
                }
            }
            if (genre.get(i) == 35) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Comedy");
                } else {
                    genreInString.add("Komedi");
                }
            }
            if (genre.get(i) == 80) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Crime");
                } else {
                    genreInString.add("Kejahatan");
                }
            }
            if (genre.get(i) == 99) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Documentary");
                } else {
                    genreInString.add("Dokumentasi");
                }
            }
            if (genre.get(i) == 18) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Drama");
                } else {
                    genreInString.add("Drama");
                }
            }
            if (genre.get(i) == 10751) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Family");
                } else {
                    genreInString.add("Keluarga");
                }
            }
            if (genre.get(i) == 14) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Fantasy");
                } else {
                    genreInString.add("Fantasi");
                }
            }
            if (genre.get(i) == 36) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("History");
                } else {
                    genreInString.add("Sejarah");
                }
            }
            if (genre.get(i) == 27) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Horror");
                } else {
                    genreInString.add("Hantu");
                }
            }
            if (genre.get(i) == 10402) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Music");
                } else {
                    genreInString.add("Musik");
                }
            }
            if (genre.get(i) == 9468) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Mystery");
                } else {
                    genreInString.add("Ilmiah");
                }
            }
            if (genre.get(i) == 878) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Science Fiction");
                } else {
                    genreInString.add("Fiksi Ilmiah");
                }
            }
            if (genre.get(i) == 10770) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("TV Movie");
                } else {
                    genreInString.add("Tayangan TV");
                }
            }
            if (genre.get(i) == 53) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Thriller");
                } else {
                    genreInString.add("Menegangkan");
                }
            }
            if (genre.get(i) == 10752) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("War");
                } else {
                    genreInString.add("Perang");
                }
            }
            if (genre.get(i) == 37) {
                if (currentLanguage.equalsIgnoreCase("en-US")) {
                    genreInString.add("Western");
                } else {
                    genreInString.add("Budaya barat");
                }
            }
        }
        return genreInString;
    }

}
