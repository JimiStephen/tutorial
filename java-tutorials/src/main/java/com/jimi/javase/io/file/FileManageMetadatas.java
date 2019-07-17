package com.jimi.javase.io.file;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;

public class FileManageMetadatas {
    public static void main(String[] args) throws Exception {
//        fileBasicAtrributues();
//        setFileTimestamp();
//        dosFileAttributes();
//        getOwnerGrouper();
//        setOwnerGroup();
//        setOwnerGroup1();
//        setMimeAttributes();
//        getMimeAttributes();
        getFileStore();
    }

    private static void getFileStore() throws IOException {
        Path file = Paths.get("D:/IDE");
        FileStore store = Files.getFileStore(file);

        long total = store.getTotalSpace() / 1024;
        long used = (store.getTotalSpace() -
                store.getUnallocatedSpace()) / 1024;
        long avail = store.getUsableSpace() / 1024;
    }

    private static void getMimeAttributes() throws IOException {
        Path file = Paths.get("mime.file");
        UserDefinedFileAttributeView view = Files
                .getFileAttributeView(file,UserDefinedFileAttributeView.class);
        String name = "user.mimetype";
        ByteBuffer buf = ByteBuffer.allocate(view.size(name));
        view.read(name, buf);
        buf.flip();
        String value = Charset.defaultCharset().decode(buf).toString();
    }

    private static void setMimeAttributes() throws IOException {
        Path file = Paths.get("mime.file");
        UserDefinedFileAttributeView view = Files
                .getFileAttributeView(file, UserDefinedFileAttributeView.class);
        view.write("user.mimetype",
                Charset.defaultCharset().encode("text/html"));
    }

    private static void setOwnerGroup1() throws IOException {
        //        There is no special-purpose method in the Files class for setting a group owner. However, a safe way to do so directly is through the POSIX file attribute view, as follows:
        Path file = Paths.get("permession.file");
        GroupPrincipal group =
                file.getFileSystem().getUserPrincipalLookupService()
                        .lookupPrincipalByGroupName("green");
        Files.getFileAttributeView(file, PosixFileAttributeView.class)
                .setGroup(group);
    }

    private static void setOwnerGroup() throws IOException {
        Path file =  Paths.get("permession.file");
        UserPrincipal owner = file.getFileSystem().getUserPrincipalLookupService()
                .lookupPrincipalByName("sally");
        Files.setOwner(file, owner);
    }

    private static void getOwnerGrouper() throws IOException {
        Path file = Paths.get("posix.file");
        PosixFileAttributes attr =
                Files.readAttributes(file, PosixFileAttributes.class);
        System.out.format("%s %s %s%n",
                attr.owner().getName(),
                attr.group().getName(),
                PosixFilePermissions.toString(attr.permissions()));
    }

    private static void dosFileAttributes() throws IOException {
        Path file = Paths.get("/home/dosfile.txt");
        try {
            DosFileAttributes attr =
                    Files.readAttributes(file, DosFileAttributes.class);
            System.out.println("isReadOnly is " + attr.isReadOnly());
            System.out.println("isHidden is " + attr.isHidden());
            System.out.println("isArchive is " + attr.isArchive());
            System.out.println("isSystem is " + attr.isSystem());
        } catch (UnsupportedOperationException x) {
            System.err.println("DOS file" +
                    " attributes not supported:" + x);
        }
    }

    private static void setFileTimestamp() throws IOException {
        Path file = new File("time.txt").toPath();
        BasicFileAttributes attr =
                Files.readAttributes(file, BasicFileAttributes.class);
        long currentTime = System.currentTimeMillis();
        FileTime ft = FileTime.fromMillis(currentTime);
        Files.setLastModifiedTime(file, ft);
    }

    private static void fileBasicAtrributues() throws IOException {
        Path file = Paths.get(".");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
    }


}
