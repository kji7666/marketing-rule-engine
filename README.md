# Marketing Rule Engine (簡易版行銷活動規則引擎)

## 專案簡介 (Project Introduction)

這是一個以實踐**組合模式 (Composite Pattern)** 和**規格模式 (Specification Pattern)** 為核心的 Java 專案。

本專案旨在建構一個輕量級但高度靈活的規則引擎。該引擎能夠根據傳入的上下文資料（如使用者資訊和訂單詳情），動態地評估複雜的、可組合的業務規則，最終回傳一個「是/否」的判斷結果。

其核心價值在於展示如何將抽象的業務規則「物件化」，並透過樹狀結構將它們組合成任意複雜的邏輯，從而實現規則的**動態配置**與**高度可重用性**。

## 核心場景 (Core Scenario)

模擬一個電商平台的行銷活動判斷場景：

> 當一個使用者進行結帳時，系統需要根據他身上的「標籤」（如：新使用者、VIP會員）以及他訂單的「屬性」（如：總金額），來判斷他是否滿足某個特定優惠活動的資格。

例如，判斷是否滿足「VIP會員的滿千九折」活動，其規則可被拆解為：
- **條件一**: 使用者的標籤包含 "VIP"。
- **條件二**: 訂單的總金額大於等於 1000 元。
- **組合邏輯**: 條件一 **且 (AND)** 條件二必須同時成立。

## 設計模式應用 (Design Patterns Used)

本專案是**組合模式**和**規格模式**這對「天作之合」的經典應用範例。

### 1. 組合模式 (Composite Pattern) - 規則的「骨架」

組合模式提供了整個規則引擎的結構基礎，讓我們能夠以統一的方式處理簡單和複雜的規則。

- **抽象元件 (Component)**: `ISpecification` 介面。這是所有規則的通用藍圖，定義了 `isSatisfiedBy()` 這個標準化的評估方法。
- **葉子節點 (Leaf)**: `UserTagSpecification`, `OrderAmountSpecification`。代表最基礎、不可再分的原子規則。
- **樹枝/容器節點 (Composite)**: `AndSpecification`, `OrSpecification`。代表邏輯運算子，它們內部可以包含多個「葉子節點」或其他「樹枝節點」，並負責將子節點的評估結果進行邏輯組合。

**帶來的好處**：**透明性**。客戶端無需關心正在處理的是一個單一規則還是一棵複雜的規則樹，都可以用完全相同的方式呼叫 `isSatisfiedBy()` 方法。

### 2. 規格模式 (Specification Pattern) - 規則的「血肉」

規格模式將抽象的業務規則變成了具體的、可操作的物件，為組合模式的骨架填充了業務邏輯。

- **規格 (Specification)**: `ISpecification` 介面及其所有實現類，都體現了規格模式。每個類別都封裝了一項明確的業務判斷邏輯。
- **候選物件 (Candidate)**: `User` 和 `Order` 物件。它們是被規則檢查的對象。
- **可組合 (Combinable)**: 規格模式的精髓。透過 `AndSpecification` 和 `OrSpecification`，我們可以像搭積木一樣，將簡單的規格組合成更複雜、更強大的新規格。

**帶來的好處**：**業務規則的物件化**。這使得規則變得可重用、可配置，並且讓程式碼 `new AndSpecification(isVip, isHighValue)` 像自然語言一樣易於理解。

## 專案結構 (Project Structure)

```
src/main/java/marketing/rule/engine/
├── context/      # 存放上下文物件 (User, Order)，即被規則判斷的資料
└── rule/         # 規則引擎的核心實現
    ├── specification/  # 抽象元件(ISpecification)和葉子節點
    └── operator/       # 樹枝節點 (AndSpecification, OrSpecification)
```

## 模式協作流程圖 (Pattern Collaboration Diagram)

```mermaid
graph TD
    subgraph Client Layer
        A[Client/API]
    end

    subgraph Rule Engine
        B{ISpecification (Rule Tree)}
    end

    subgraph Context
        C[User]
        D[Order]
    end

    A -- "evaluate(rule, user, order)" --> B
    B -- "isSatisfiedBy(user, order)" --> C
    B -- "isSatisfiedBy(user, order)" --> D
```

## 如何執行 (How to Run)

### 環境需求 (Prerequisites)
- JDK 17 或更高版本
- Gradle 8.0 或更高版本

### 執行範例 (Running the Example)

本專案的 `RuleEngineApplication.java` 包含一個 `main` 方法，清晰地展示了如何：
1.  定義基礎的「葉子」規則。
2.  使用 `AndSpecification` 和 `OrSpecification` 將它們組合成複雜的「樹狀」規則。
3.  將不同的使用者和訂單資料傳入規則樹，進行評估並觀察結果。

您可以直接在 IDE 中執行此檔案來查看運行效果。

### 執行單元測試 (Running Tests)

專案包含了對各個規則元件的單元測試。您可以透過以下指令執行所有測試：
```bash
# For Windows
.\gradlew test

# For macOS / Linux
./gradlew test
```
