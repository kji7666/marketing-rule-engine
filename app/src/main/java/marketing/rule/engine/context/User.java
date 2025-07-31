package marketing.rule.engine.context;

import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

/**
 * 使用者上下文物件 (Context)
 * 一個簡單的 POJO，用於存放使用者相關的資料，特別是他們的標籤。
 */
public class User {

    private String userId;
    private Set<String> tags; // 使用 Set 來存放使用者標籤，如 "VIP", "NEW_USER"

    /**
     * User 的建構函式
     * @param userId 使用者 ID
     * @param tags   一個包含使用者標籤的 Set 集合
     */
    public User(String userId, Set<String> tags) {
        this.userId = userId;
        // 進行防禦性拷貝，確保傳入的 Set 在外部被修改時，不會影響到 User 物件內部的狀態
        this.tags = (tags != null) ? new HashSet<>(tags) : Collections.emptySet();
    }

    // --- Getters ---

    public String getUserId() {
        return userId;
    }

    public Set<String> getTags() {
        // 返回一個不可變的拷貝，防止外部程式碼直接修改 User 的標籤列表
        return Collections.unmodifiableSet(tags);
    }
    
    // --- (可選) 提供一個方便的方法來檢查標籤 ---
    
    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }
}