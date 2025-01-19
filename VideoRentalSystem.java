import java.util.ArrayList;
import java.util.Scanner;


class Video {
    private String title;
    private boolean isAvailable;
    private ArrayList<Integer> ratings;

    public Video(String title) {
        this.title = title;
        this.isAvailable = true;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }
                               
    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Error: Video is already rented out.");
        }
    }

    public void returnVideo() {
        if (!isAvailable) {
            isAvailable = true;
        } else {
            System.out.println("Error: Video was not rented.");
        }
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        } else {
            System.out.println("Error: Rating must be between 1 and 5.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (int rating : ratings) {
            total += rating;
        }
        return total / ratings.size();
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Available: " + (isAvailable ? "Yes" : "No") +
                " | Average Rating: " + String.format("%.2f", getAverageRating());
    }
}


class VideoStore {
    private ArrayList<Video> inventory;

    public VideoStore() {
        inventory = new ArrayList<>();
    }

    public void addVideo(String title) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Error: Video already exists in the inventory.");
                return;
            }
        }
        inventory.add(new Video(title));
        System.out.println("Video added successfully: " + title);
    }

    public void listInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No videos in inventory.");
        } else {
            System.out.println("Inventory:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println((i + 1) + ". " + inventory.get(i));
            }
        }
    }

    public void rentVideo(String title) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                if (video.isAvailable()) {
                    video.rent();
                    System.out.println("You rented: " + title);
                } else {
                    System.out.println("Video is currently unavailable.");
                }
                return;
            }
        }
        System.out.println("Error: Video not found in inventory.");
    }

    public void returnVideo(String title) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                if (!video.isAvailable()) {
                    video.returnVideo();
                    System.out.println("You returned: " + title);
                } else {
                    System.out.println("Error: Video was not rented.");
                }
                return;
            }
        }
        System.out.println("Error: Video not found in inventory.");
    }

    public void addRating(String title, int rating) {
        for (Video video : inventory) {
            if (video.getTitle().equalsIgnoreCase(title)) {
                video.addRating(rating);
                System.out.println("Rating added for " + title);
                return;
            }
        }
        System.out.println("Error: Video not found in inventory.");
    }
}


public class VideoRentalSystem {
    public static void main(String[] args) {
        VideoStore store = new VideoStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Video Rental Store ---");
            System.out.println("1. Add Video");
            System.out.println("2. List Inventory");
            System.out.println("3. Rent Video");
            System.out.println("4. Return Video");
            System.out.println("5. Add Rating to Video");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter video title to add: ");
                    String titleToAdd = scanner.nextLine().trim();
                    store.addVideo(titleToAdd);
                    break;
                case 2:
                    store.listInventory();
                    break;
                case 3:
                    System.out.print("Enter video title to rent: ");
                    String titleToRent = scanner.nextLine().trim();
                    store.rentVideo(titleToRent);
                    break;
                case 4:
                    System.out.print("Enter video title to return: ");
                    String titleToReturn = scanner.nextLine().trim();
                    store.returnVideo(titleToReturn);
                    break;
                case 5:
                    System.out.print("Enter video title to rate: ");
                    String titleToRate = scanner.nextLine().trim();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    store.addRating(titleToRate, rating);
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
