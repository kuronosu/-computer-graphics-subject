package graphs;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kuro
 */
public class Utils {

    public static <T> List<List<T>> zip(List<T>... lists) {
        List<List<T>> zipped = new ArrayList<>();
        for (List<T> list : lists) {
            for (int i = 0, listSize = list.size(); i < listSize; i++) {
                List<T> list2;
                if (i >= zipped.size()) {
                    zipped.add(list2 = new ArrayList<>());
                } else {
                    list2 = zipped.get(i);
                }
                list2.add(list.get(i));
            }
        }
        return zipped;
    }

    public static <E> List<E> removeDuplicates(List<E> originalList) {
        ArrayList<E> result = new ArrayList<>();
        for (E el : originalList) {
            if (!result.contains(el)) {
                result.add(el);
            }
        }
        return result;
    }

    public static Point rotatePoint(Point point, Point center, double a) {
        a = Math.toRadians(a);
        int dx = point.getX() - center.getX();
        int dy = point.getY() - center.getY();
        double ca = Math.cos(a);
        double sa = Math.sin(a);
        return new Point((int) (ca * dx - sa * dy) + center.getX(),
                (int) (sa * dx + ca * dy) + center.getY());
    }
}
