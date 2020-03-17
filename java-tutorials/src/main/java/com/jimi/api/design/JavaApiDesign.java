package com.jimi.api.design;

import javax.swing.text.Segment;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author xianyao.ye@ucarinc.com
 * @version 1.0
 * @date 2020/3/11 10:33
 */
public class JavaApiDesign {

    private String comment;
    private String[] comments;

    public Optional<String> getComment() {
        return Optional.ofNullable(comment);
    }

//    public String getComment() {
//        return comment; // comment is nullable
//    }


    public Stream<String> comments() {
        return Stream.of(comments);
    }


//    public String[] comments() {
//        return comments; // Exposes the backing array!
//    }


    public static void main(String[] args) {
//        Point point = Point.of(1,2);

//        Point point = new PointImpl(1,2);

//
//        Reader reader = Reader.builder()
//                .withErrorHandler(IOException::printStackTrace)
//                .build();

//        Reader reader = new AbstractReader() {
//            @Override
//            public void handleError(IOException ioe) {
//                ioe. printStackTrace();
//            }
//        };

    }


//    @FunctionalInterface
//    public interface CircleSegmentConstructor {
//        CircleSegment apply(Point cntr, Point p, double ang);
//        // abstract methods cannot be added
//    }

//    public interface CircleSegmentConstructor {
//        CircleSegment apply(Point cntr, Point p, double ang);
//        // abstract methods may be accidently added later
//    }

    public interface Point {
       void addRenderer(Function<Point, String> renderer);
       void addLogCondition(Predicate<Point> logCondition);
    }
//    public interface Point {
//        add(Function<Point, String> renderer);
//        add(Predicate<Point> logCondition);
//    }

    public interface Line {
        Point start();
        Point end();
        int length();
    }

//    public interface Line {
//        Point start();
//        Point end();
//        default int length() {
//            int deltaX = start().x() - end().x();
//            int deltaY = start().y() - end().y();
//            return (int) Math.sqrt(
//                    deltaX * deltaX + deltaY * deltaY
//            );
//        }
//    }

//    public void addToSegment(Segment segment, Point point) {
//        Objects.requireNonNull(segment);
//        Objects.requireNonNull(point);
//        segment.add(point);
//    }
//
//    public void addToSegment(Segment segment, Point point) {
//        segment.add(point);
//    }

    private void allApi(){

//    Optional<String> comment = // some Optional value
//            String guiText = comment
//            .map(c -> "Comment: " + c)
//            .orElse("");
//
//    Optional<String> comment = // some Optional value
//            String guiText = "Comment: " + comment.get();
//
//    Stream.of("this", "is", "secret")
//            .map(toGreek())
//            .map(encrypt())
//            .collect(joining(" "));
//
//            Stream.of("this", "is", "secret").map(toGreek()).map(encrypt()).collect(joining(" "));
    }

}
