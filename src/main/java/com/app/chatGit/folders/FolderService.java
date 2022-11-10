package io.chatgitapp.folders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FolderService {


    @Autowired
    private UnreadEmailStatsRepository unreadEmailStatsRepository;

    public List<Folder> fetchDefaultFolders(String userId){
        return Arrays.asList(
                new Folder(userId, "Inbox", "white"),
                new Folder(userId, "Sent", "green"),
                new Folder(userId, "Important", "red")
        );
    }

    public Map<String, Integer> mapCountToLabels(String userId){
        List<UnreadEmailStats> stats = unreadEmailStatsRepository.findAllById(userId);
        return stats.stream()
                .collect(Collectors.toMap(UnreadEmailStats::getLabel, UnreadEmailStats::getUnreadCount));
    }
}