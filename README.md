# SpendWise - 现代简约记账应用

SpendWise 是一款基于 Android 平台，采用最新技术栈打造的简约记账工具。旨在帮助用户通过直观的交互和清晰的数据看板，轻松管理个人财务。

## ✨ 功能特性

- **快速记账**：流式交互，支持 Emoji 分类图标，让记账不再枯燥。
- **智能看板**：首页实时汇总本月总支出与记账笔数，财务状况一目了然。
- **即时搜索**：支持按标题或分类实时筛选账单。
- **灵活编辑**：支持账单的随时修改与删除。
- **本地存储**：基于 Room 数据库，确保数据安全并支持离线使用。
- **现代架构**：采用 Clean Architecture + MVVM，代码结构清晰，易于扩展。

## 🛠️ 技术栈

- **UI 框架**：[Jetpack Compose](https://developer.android.com/jetpack/compose) (声明式 UI)
- **架构模式**：Clean Architecture + MVVM
- **依赖注入**：[Hilt](https://dagger.dev/hilt/) (Dagger Android 优化版)
- **数据库**：[Room](https://developer.android.com/training/data-storage/room)
- **异步处理**：Kotlin Coroutines & Flow
- **导航**：Compose Navigation
- **构建系统**：Gradle (Kotlin DSL + Version Catalog)

## 📸 屏幕截图

*(此处可添加应用截图)*

## 🚀 快速开始

1. **克隆项目**
   ```bash
   git clone https://github.com/wangshiqi368/SpendWise.git
   ```
2. **使用 Android Studio 打开**
   推荐使用最新的 Android Studio Hedgehog 或更高版本。
3. **运行应用**
   连接模拟器或真机，点击 `Run` 即可体验。

## 📅 开发计划 (Roadmap)

- [x] 核心记账功能 (CRUD)
- [x] 主页数据汇总看板
- [x] 分类图标选择器 (Emoji)
- [x] 账单搜索与筛选
- [x] 月度预算管理与进度条
- [x] 支出趋势统计图表 (饼图/柱状图)
- [x] 数据导出为 CSV 文件
- [ ] **Next**: 深色模式主题适配
- [ ] **Next**: 多币种支持与汇率换算
- [ ] **Next**: 账单云同步功能

## 📄 开源协议

本项目采用 [MIT License](LICENSE) 开源协议。
