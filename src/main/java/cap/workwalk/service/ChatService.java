package cap.workwalk.service;

import cap.workwalk.dto.ChatDto;
import cap.workwalk.entity.Chat;
import cap.workwalk.entity.User;
import cap.workwalk.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ChatService {

    final ChatRepository chatRepository;

    @Transactional
    public Integer savePost(ChatDto chatDto) {
        return chatRepository.save(chatDto.toEntity()).getId();
    }

    @Transactional
    public Page<Chat> findBySend(User send, Pageable pageable) {
        return chatRepository.findBySend(send, pageable);
    }

    @Transactional
    public Page<Chat> findByReceive(User receive, Pageable pageable) {
        return chatRepository.findByReceive(receive, pageable);
    }

}
