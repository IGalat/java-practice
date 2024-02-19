package worktests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OmersCodility2 {

    public static class Photo {
        private int ordinal;
        private String extension;
        private String city;
        private String dateTimeStr;
        private String newOrdinal;

        public int getOrdinal() {
            return ordinal;
        }

        public void setOrdinal(int ordinal) {
            this.ordinal = ordinal;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDateTimeStr() {
            return dateTimeStr;
        }

        public void setDateTimeStr(String dateTimeStr) {
            this.dateTimeStr = dateTimeStr;
        }

        public String getNewOrdinal() {
            return newOrdinal;
        }

        public void setNewOrdinal(String newOrdinal) {
            this.newOrdinal = newOrdinal;
        }
    }

    public String solution(String S) {
        List<Photo> photos = parsePhotos(S);
        photos.stream()
                .collect(Collectors.groupingBy(Photo::getCity))
                .forEach((city, byCity) -> addNewOrdinals(byCity));
        return photos.stream()
                .map(photo -> photo.getCity() + photo.getNewOrdinal() + "." + photo.getExtension())
                .collect(Collectors.joining("\n"));
    }

    private List<Photo> parsePhotos(String input) {
        String[] lines = input.split("\n");
        List<Photo> result = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            Photo photo = new Photo();
            photo.setOrdinal(i);

            String[] commaSplit = line.split(", ");
            String[] nameExt = commaSplit[0].split("\\.");
            photo.setExtension(nameExt[1]);
            photo.setCity(commaSplit[1]);
            photo.setDateTimeStr(commaSplit[2]);

            result.add(photo);
        }
        return result;
    }

    private void addNewOrdinals(List<Photo> photos) {
        photos.sort(Comparator.comparing(Photo::getDateTimeStr));
        int newOrdLen = String.valueOf(photos.size()).length();
        for (int i = 0; i < photos.size(); i++) {
            StringBuilder newOrd = new StringBuilder(Integer.toString(i + 1));
            while (newOrd.length() < newOrdLen) {
                newOrd.insert(0, "0");
            }
            photos.get(i).setNewOrdinal(newOrd.toString());
        }
    }

    public static void main(String[] args) {
        String input = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11";

        String expected = "Warsaw02.jpg\n" +
                "London1.png\n" +
                "Warsaw01.png\n" +
                "Paris2.jpg\n" +
                "Paris1.jpg\n" +
                "London2.jpg\n" +
                "Paris3.png\n" +
                "Warsaw03.jpg\n" +
                "Warsaw09.png\n" +
                "Warsaw07.jpg\n" +
                "Warsaw06.jpg\n" +
                "Warsaw08.jpg\n" +
                "Warsaw04.png\n" +
                "Warsaw05.png\n" +
                "Warsaw10.jpg";

        System.out.println(new OmersCodility2().solution(input));
        System.out.println(expected.equals(new OmersCodility2().solution(input)));
    }
}
