# Лабораторна робота. Варіант №14
## Зміст
* [Загальний опис](#Description)
* [Клас контролер - AccessDevice](#Controller)
	* [IDisplay](#IDisplay)
	* [Memory](#Memory)
	* [ButtonsPanel](#ButtonsPanel)
	* [AccessCardRecipient](#AccessCardRecipient)
	* [Bell](#Bell)
	* [ElectromechanicalLock](#ElectromechanicalLock)
	* [Port](#Port)
* [AccessDeviceDAO](#AccessDeviceDAO)
* [Схема бази даних](#DataBase)
* [Патерн State](#State)

## <a name="Description"></a>Загальний опис
**Автор:** Язенок Михайло, ІП-61

**Умова:** [Варіант №14](https://github.com/MihailYa/access_device_java/blob/master/%D0%9B%D0%B0%D0%B1%D0%BE%D1%80%D0%B0%D1%82%D0%BE%D1%80%D0%BD%D0%B0%D1%8F%20%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%201_2.pdf)

**Посилання на повну UML діаграму:** [Whole_architecture.png](https://github.com/MihailYa/access_device_java/blob/master/architecture/Whole_architecture.png "https://github.com/MihailYa/access_device_java/blob/master/architecture/Whole_architecture.png")

**В лабораторній роботі було використано:**
* Патерн MVC - для загальної структури та взаємодії.
	* [Клас AccessDevice](#Controller) - Controller
	* [Клас Memory](#Memory) - Model
	* [Інтерфейс IDisplay (та реалізація Display)](#IDisplay) - View
* [Патерн DAO](#DAO) - для отримання доступу до бази даних (вибірку, оновлення, створення та видалення даних)
* [Патерн State](#State) - для явного та зручного переходу між станами пропускного пристрою


## <a name="Controller"></a>Клас контролер - AccessDevice
![AccessDevice](https://github.com/MihailYa/access_device_java/blob/master/architecture/AccessDevice\(Controller\).png)

Включає наступні класи:
![MVC](https://github.com/MihailYa/access_device_java/blob/master/architecture/MVC.png "MVC")

### <a name="IDisplay"></a>IDisplay
![IDisplay](https://github.com/MihailYa/access_device_java/blob/master/architecture/IDisplay.png)

**IDisplay** (помічено зеленим) – інтерфейс графічного дисплею, яка виступає у ролі *View*

### <a name="Memory"></a>Memory
![Memory](https://github.com/MihailYa/access_device_java/blob/master/architecture/Memory.png "Memory")

**Memory** (помічено жовтим) – клас внутрішньої пам’яті пристрою, який виступає у ролі *Model*

Відповідає за взаємодію з базою даних (через **AccessDeviceDAO**) та зберігання кодів (коду доступу і коду контроля). Дані картки зберігаються у класі **AccessCard** (дані про людину та розклад за яким ця людина може відвідувати приміщення)

### <a name="ButtonsPanel"></a>ButtonsPanel
![ButtonsPanel](https://github.com/MihailYa/access_device_java/blob/master/architecture/ButtonsPanel.png "ButtonsPanel")

**ButtonsPanel** – клас панелі керування з кнопками, відповідає за введення даних і повідомлення контролеру (через інтерфейс **IButtonsPanelEventsRecivier**) про натиснуту/зажату/віджату кнопку. Також контролює стан кнопок (допомагає уникнути ситуації, коли одну і ту саму кнопку двічі зажимають).

### <a name="AccessCardRecipient"></a>AccessCardRecipient
![AccessCardRecipient ](https://github.com/MihailYa/access_device_java/blob/master/architecture/AccessCardRecipient.png "AccessCardRecipient ")

**AccessCardRecipient** – клас приймача карток, відповідає за отримання картки (та повідомлення контролер через **ICardDataRecivier** про отримання картки), повернення картки та блокування картки.

### <a name="Bell"></a>Bell
![Bell](https://github.com/MihailYa/access_device_java/blob/master/architecture/bell.png "Bell")

**Bell** – клас дзвінка, відповідає за подавання звукового сигналу всередині приміщення.

### <a name="ElectromechanicalLock"></a>ElectromechanicalLock 
![ElectromechanicalLock ](https://github.com/MihailYa/access_device_java/blob/master/architecture/ElectromechanicalLock.png "ElectromechanicalLock ")

**ElectromechanicalLock** – клас електромеханічного замка, відповідає за блокування і відкриття дверей.

### <a name="Port"></a>Port
![Port](https://github.com/MihailYa/access_device_java/blob/master/architecture/Port.png "Port")

**Port** – клас фізичного порту, за допомогою якого персональний комп’ютер отримує повний доступ до бази даних через **AccessDeviceDAO**.

## <a name="DAO"></a>AccessDeviceDAO
![AccessDeviceDAO](https://github.com/MihailYa/access_device_java/blob/master/architecture/AccessDeviceDAO.png "AccessDeviceDAO")

**AccessDeviceDAO** (помічено помаранчевим) – клас для доступу до бази даних, який включає в себе класи:
- **AccessCardDAO** - для доступу до карток користувачів
	- **ScheduleDAO** – для доступу до таблиці розкладу конкретного користувача
	- **PersonDAO** – для доступу до таблиці користувачів
- **VisitorsJournalDAO** - для доступу до журналу відвідувань користувачами приміщення
- **LockedCardsJournalDAO** – для доступу до журналу блокування карток

## <a name="DataBase"></a>Схема бази даних
![DataBase](https://github.com/MihailYa/access_device_java/blob/master/architecture/DataBase.png "DataBase")

## <a name="State"></a>Патерн State
У контролері (AccessDevice) використовується патерн «State», для зберігання поточного стану пропускного пристрою.

Класи станів:
![States](https://github.com/MihailYa/access_device_java/blob/master/architecture/AccessDeviceState.png "States")

Схема переходу між станами:
![States flow](https://github.com/MihailYa/access_device_java/blob/master/architecture/AccessDeviceState_flow.png)