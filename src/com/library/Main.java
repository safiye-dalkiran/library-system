package com.library;

import com.library.book.*;
import com.library.book.Book;
import com.library.catalogue.Author;
import com.library.catalogue.Category;
import com.library.person.Librarian;
import com.library.person.Member;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        System.out.println("Sistem başlatılıyor...");


        try {
            Author a1 = new Author("Orhan Pamuk");
            Author a2 = new Author("National Geographic");

            Category cNovel = new Category("Roman");
            Category cMag = new Category("Bilim Dergisi");

            int id1 = lib.getCatalogue().generateId();
            Book b1 = new Novel(id1, "Masumiyet Müzesi", a1, cNovel, 3);
            lib.getCatalogue().addBook(b1);

            int id2 = lib.getCatalogue().generateId();
            Book b2 = new Magazine(id2, "NatGeo Ekim Sayısı", a2, cMag, 10);
            lib.getCatalogue().addBook(b2);

            lib.addMember(new Member(1, "Safiye Dalkıran", "safiye@mail.com"));
            lib.addLibrarian(new Librarian(100, "Admin", "admin@lib.com", "123456"));

        } catch (Exception e) {
            System.out.println("Veri yükleme hatası: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n################################");
            System.out.println("=== KÜTÜPHANE YÖNETİM SİSTEMİ ===");
            System.out.println("1. Yeni Materyal Ekle (Kitap/Dergi vb.)"); // İsim güncellendi
            System.out.println("2. Materyal Ara");
            System.out.println("3. Yeni Üye Kayıt Et");
            System.out.println("4. Ödünç Al");
            System.out.println("5. İade Et");
            System.out.println("6. Üye Durumu Sorgula");
            System.out.println("7. Tüm Kataloğu Listele");
            System.out.println("8. Rapor: Gecikmiş İadeler");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Librarian ID: ");
                        int libId = Integer.parseInt(sc.nextLine());

                        if (!lib.isLibrarian(libId)) {
                            System.out.println("HATA: Yetkisiz giriş!");
                            break;
                        }

                        System.out.print("Başlık: ");
                        String title = sc.nextLine();
                        System.out.print("Yazar/Yayıncı Adı: ");
                        String authorName = sc.nextLine();
                        System.out.print("Kategori Adı: ");
                        String catName = sc.nextLine();
                        System.out.print("Stok Adedi: ");
                        int copies = Integer.parseInt(sc.nextLine());

                        System.out.println("Materyal Türü Seçiniz:");
                        System.out.println("N) Novel (Roman)");
                        System.out.println("S) StudyBooks (Ders Kitabı)");
                        System.out.println("M) Magazine (Dergi)");
                        System.out.println("J) Journal (Akademik Dergi)");
                        System.out.print("Seçim (N/S/M/J): ");
                        String type = sc.nextLine().toUpperCase();

                        int newId = lib.getCatalogue().generateId();

                        Author author = new Author(authorName);
                        Category category = new Category(catName);

                        Book newBook = null;

                        switch (type) {
                            case "N":
                                newBook = new Novel(newId, title, author, category, copies);
                                break;
                            case "S":
                                newBook = new StudyBook(newId, title, author, category, copies);
                                break;
                            case "M":
                                newBook = new Magazine(newId, title, author, category, copies);
                                break;
                            case "J":
                                newBook = new Journal(newId, title, author, category, copies);
                                break;
                            default:
                                System.out.println("Geçersiz tür seçimi! İşlem iptal edildi.");
                                break;
                        }

                        if (newBook != null) {
                            lib.getCatalogue().addBook(newBook);
                            System.out.println(">> Başarılı: " + newBook.getClass().getSimpleName() + " eklendi. ID: " + newId);
                        }
                        break;

                    case "2":
                        System.out.println("Arama Tipi: a) ID  b) Yazar  c) Kategori");
                        String searchType = sc.nextLine();

                        if (searchType.equalsIgnoreCase("a")) {
                            System.out.print("ID: ");
                            int searchId = Integer.parseInt(sc.nextLine());
                            Book found = lib.getCatalogue().getById(searchId);
                            System.out.println(found != null ? found : ">> Bulunamadı.");
                        } else if (searchType.equalsIgnoreCase("b")) {
                            System.out.print("Yazar: ");
                            var books = lib.getCatalogue().getByAuthor(sc.nextLine());
                            if(books.isEmpty()) System.out.println(">> Bulunamadı.");
                            books.forEach(System.out::println);
                        } else if (searchType.equalsIgnoreCase("c")) {
                            System.out.print("Kategori: ");
                            var books = lib.getCatalogue().getByCategory(sc.nextLine());
                            if(books.isEmpty()) System.out.println(">> Bulunamadı.");
                            books.forEach(System.out::println);
                        }
                        break;

                    case "3":
                        System.out.print("Üye ID: ");
                        int mid = Integer.parseInt(sc.nextLine());
                        System.out.print("İsim: ");
                        String mname = sc.nextLine();
                        System.out.print("Email: ");
                        String memail = sc.nextLine();
                        lib.addMember(new Member(mid, mname, memail));
                        System.out.println(">> Üye eklendi.");
                        break;

                    case "4":
                        System.out.print("Üye ID: ");
                        int borrowerId = Integer.parseInt(sc.nextLine());
                        System.out.print("Kitap/Materyal ID: ");
                        int bookId = Integer.parseInt(sc.nextLine());
                        System.out.println(">> " + lib.borrowBook(borrowerId, bookId));
                        break;

                    case "5":
                        System.out.print("Üye ID: ");
                        int returnerId = Integer.parseInt(sc.nextLine());
                        System.out.print("Kitap/Materyal ID: ");
                        int rBookId = Integer.parseInt(sc.nextLine());
                        System.out.println(">> " + lib.returnBook(returnerId, rBookId));
                        break;

                    case "6":
                        System.out.print("Üye ID: ");
                        Member m = lib.getMember(Integer.parseInt(sc.nextLine()));
                        if (m != null) {
                            System.out.println(m);
                            System.out.println(m.getRecord());
                            System.out.println("Aldığı Kitaplar: " + m.getBorrowedBookIds());
                        } else {
                            System.out.println("Üye yok.");
                        }
                        break;

                    case "7":
                        System.out.println("--- TÜM KATALOG ---");
                        lib.getCatalogue().getAllBooks().forEach(System.out::println);
                        break;

                    case "8":
                        System.out.println("--- GECİKMİŞ İADELER ---");
                        var overdue = lib.getLoanManager().getOverdueLoans();
                        if (overdue.isEmpty()) System.out.println("Gecikme yok.");
                        else overdue.forEach(System.out::println);
                        break;

                    case "0":
                        System.out.println("Çıkış yapılıyor...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Geçersiz seçim.");
                }
            } catch (NumberFormatException e) {
                System.out.println("HATA: Lütfen sayı giriniz.");
            } catch (Exception ex) {
                System.out.println("HATA: " + ex.getMessage());
            }
        }
    }
}