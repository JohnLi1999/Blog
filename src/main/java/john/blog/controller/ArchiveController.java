package john.blog.controller;

import john.blog.domain.Album;
import john.blog.domain.Blog;
import john.blog.domain.Mood;
import john.blog.dto.TimeLineObject;
import john.blog.service.AlbumService;
import john.blog.service.BlogService;
import john.blog.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MoodService moodService;

    @RequestMapping("/archives")
    public ModelAndView archives(Model model) {
        // Prepare Data
        List<Blog> blogList = blogService.findAllBlogsOrderByCreatedTime();
        List<Album> albumList = albumService.findAllAlbumsOrderByCreatedTime();
        List<Mood> moodList = moodService.findAllMoodsOrderByCreatedTime();
        List<String> blogTimeLine = blogService.findBlogTimeLine();
        List<String> albumTimeLine = albumService.findAlbumTimeLine();
        List<String> moodTimeLine = moodService.findMoodTimeLine();

        // Update the format of date
        blogTimeLine = updateDateToYYYYMM(blogTimeLine);
        albumTimeLine = updateDateToYYYYMM(albumTimeLine);
        moodTimeLine = updateDateToYYYYMM(moodTimeLine);

        // Create Time Line
        List<String> timeLineStringsTemp = merge(blogTimeLine.toArray(new String[0]), albumTimeLine.toArray(new String[0]));
        List<String> timeLine = merge(timeLineStringsTemp.toArray(new String[0]), moodTimeLine.toArray(new String[0]));

        List<TimeLineObject>[] timeLineObjectLists = convertToTimeLineObjectList(blogList, albumList, moodList);
        List<TimeLineObject> timeLineObjectsTemp = mergeTimeLineObjectLists(timeLineObjectLists[0].toArray(new TimeLineObject[0]), timeLineObjectLists[1].toArray(new TimeLineObject[0]));
        List<TimeLineObject> timeLineObjectList = mergeTimeLineObjectLists(timeLineObjectsTemp.toArray(new TimeLineObject[0]), timeLineObjectLists[2].toArray(new TimeLineObject[0]));

        // Put data into model
        model.addAttribute("timeLine", timeLine);
        model.addAttribute("timeLineObjectList", timeLineObjectList);

        return new ModelAndView("archive.html", "archiveModel", model);
    }

    private List<TimeLineObject> mergeTimeLineObjectLists(TimeLineObject[] arr1, TimeLineObject[] arr2) {
        TimeLineObject[] target = new TimeLineObject[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            // Add elements in the first array when the date is larger or the same
            target[k++] = arr1[i].getCreatedTime().compareTo(arr2[j].getCreatedTime()) >= 0 ? arr1[i++] : arr2[j++];
        }

        if (i == arr1.length) {
            System.arraycopy(arr2, j, target, k, arr2.length - j);
        } else {
            System.arraycopy(arr1, i, target, k, arr1.length - i);
        }

        return Arrays.asList(target);
    }

    private List<TimeLineObject>[] convertToTimeLineObjectList(List<Blog> blogList, List<Album> albumList, List<Mood> moodList) {
        List<TimeLineObject>[] result = new List[3];

        List<TimeLineObject> blogTimeLineList = new ArrayList<>();
        blogList.forEach(blog -> {
            TimeLineObject tlo = new TimeLineObject();
            tlo.setCreatedTime(blog.getCreatedTime());
            tlo.setTitle(blog.getTitle());
            tlo.setHref("/blogDetail?id=" + blog.getId());
            tlo.setOther("(" + blog.getViewNumber() + " views)");
            blogTimeLineList.add(tlo);
        });
        result[0] = blogTimeLineList;


        List<TimeLineObject> albumTimeLintList = new ArrayList<>();
        albumList.forEach(album -> {
            TimeLineObject tlo = new TimeLineObject();
            tlo.setCreatedTime(album.getCreatedTime());
            tlo.setTitle(album.getTitle());
            tlo.setHref("/albumDetail?id=" + album.getId());
            tlo.setOther("(" + album.getImageNumber() + " PICS)");
            albumTimeLintList.add(tlo);
        });
        result[1] = albumTimeLintList;

        List<TimeLineObject> moodTimeLintList = new ArrayList<>();
        moodList.forEach(mood -> {
            TimeLineObject tlo = new TimeLineObject();
            tlo.setCreatedTime(mood.getCreatedTime());
            tlo.setTitle(mood.getTitle());
            tlo.setHref("/moods#" + mood.getId());
            moodTimeLintList.add(tlo);
        });
        result[2] = moodTimeLintList;

        return result;
    }

    /**
     * Create the combined list that contains all created time in the given two arrays
     * @param arr1 first arr needs be combined
     * @param arr2 second arr needs to be combined
     * @return the combined list
     */
    private List<String> merge(String[] arr1, String[] arr2) {
        String[] target = new String[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        boolean success;

        while (i < arr1.length || j < arr2.length) {
            if (i >= arr1.length) {
                success = addWithoutDuplicate(target, k, arr2[j]);
                j++;
            } else if (j >= arr2.length) {
                success = addWithoutDuplicate(target, k, arr1[i]);
                i++;
            } else {
                if (arr1[i].compareTo(arr2[j]) > 0) {
                    success = addWithoutDuplicate(target, k, arr1[i]);
                    i++;
                } else if (arr1[i].compareTo(arr2[j]) < 0) {
                    success = addWithoutDuplicate(target, k, arr2[j]);
                    j++;
                } else {
                    success = addWithoutDuplicate(target, k, arr1[i]);
                    i++;
                    j++;
                }
            }

            if (success) {
                k++;
            }
        }

        // Remove null value from the array
        String[] cleanedArr = Arrays.stream(target).filter(Objects::nonNull).toArray(String[]::new);

        return Arrays.asList(cleanedArr);
    }

    /**
     * Helper method to prevent duplicate values when combining two arrays
     * @param arr   the target array
     * @param index the index to add the new elements
     * @param str   the new element to be added
     * @return      true if the new element is added, false otherwise
     */
    private boolean addWithoutDuplicate(String[] arr, int index, String str) {
        // Directly add the string if the position is at the beginning of the array
        if (index == 0) {
            arr[index] = str;
            return true;
        }

        /* If the new element added is equal to the last element in the current array,
           return immediately. Otherwise, add the new element into the array           */
        if (!arr[index - 1].equals(str)) {
            arr[index] = str;
            return true;
        }

        return false;
    }

    /**
     * Update the date format from "YYYY-MM-DD hh:mm" to "YYYY-MM"
     * @param dates the arr of dates needs to be updated
     * @return the updated version of dates
     */
    private List<String> updateDateToYYYYMM(List<String> dates) {
        List<String> target = new ArrayList<>();
        dates.forEach(str -> {
            target.add(str.substring(0, 7));
        });
        return target;
    }
}
