package FeedbackCreationService.services;

import FeedbackCreationService.entities.Feedback;
import FeedbackCreationService.entities.Product;
import FeedbackCreationService.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void addFeedback(Double estimate, String text, Long userId, Long productId) {
        Long id = (long) (feedbackRepository.findAll().size() + 1);
        final Feedback feedback = new Feedback(id, new Date(), estimate, text, userId, productId);
        feedbackRepository.save(feedback);
    }

    @Transactional
    public void updateFeedback(Long id, Date date, Double estimate, String text, Long userId, Long productId) {
        final Feedback feedback = findFeedback(id);
        feedback.setDate(date);
        feedback.setEstimate(estimate);
        feedback.setText(text);
        feedback.setUserId(userId);
        feedback.setProductId(productId);
        feedbackRepository.save(feedback);

    }

    @Transactional
    public void deleteFeedback(Long id) {
        final Feedback feedback = findFeedback(id);
        feedbackRepository.delete(feedback);
    }

    @Transactional(readOnly = true)
    public Feedback findFeedback(Long id) {
        final Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback.orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Feedback> findAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Feedback> findProductsFeedbacks(Long productId) {
        return feedbackRepository.findByProductId(productId);
    }
}
