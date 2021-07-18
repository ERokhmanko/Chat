import java.text.SimpleDateFormat
import java.util.*

data class Chat(
    val idChat: Int = 0,
    val userFromId: Int,
    val userToId: Int,
    var messages: MutableList<Message> = mutableListOf()
)

data class Message(
    val idMessage: Int = 0,
    val userFromId: Int,
    val userToId: Int,
    val idChat: Int,
    val time: String = "",
    val text: String,
    var viewed: Boolean = false
)

data class User(
    val idUser: Int = 0,
    val nameUser: String
)

class ChatService {
    var chats = mutableListOf<Chat>()
    var users = mutableListOf<User>()

    fun addUser(nameUser: String): Int { // Возвращает созданный ID или -1, если не получилось добавить
        val id = if (users.isEmpty()) 1 else users[users.lastIndex].idUser + 1
        if (users.add(User(idUser = id, nameUser = nameUser)))
            return id
        return -1
    }

    fun getUsersList(): List<User> = users


    fun addMess(message: Message): Boolean {
        val time = SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Date())
        chats.searchChatId(message.idChat)?.apply {
            val idMessage = if (messages.isEmpty()) 1 else messages[messages.lastIndex].idMessage + 1
            messages.add(message.copy(idMessage = idMessage, time = time))
            return true
        } ?: let {
            val idChat = if (chats.isEmpty()) 1 else chats[chats.lastIndex].idChat + 1
            chats.add(
                Chat(
                    idChat = idChat,
                    userFromId = message.userFromId,
                    userToId = message.userToId,
                    mutableListOf(message.copy(idMessage = 1, time = time))
                )
            )
            return true
        }
        return false
    }

    fun delMess(idChat: Int, idMessage: Int): Boolean {
        chats.searchChatId(idChat)?.apply {
            if (messages.size > 1)
            messages.find { message -> message.idMessage == idMessage }.apply {
                return messages.remove(this) }
            else return chats.remove(this)
        }
        return false
    }

    fun editMess(idChat: Int, idMessage: Int, textEdit: String): Boolean{
        chats.searchChatId(idChat)?.apply {
            messages.find {message -> message.idMessage == idMessage }?.apply {
                messages.remove(this)
                messages.add(this.copy(text = textEdit))
                return true
            }
        }
        return false
    }

    fun getMess(idChat: Int, count: Int): List<Message> { //Показывает последние сообщения
       return chats.searchChatId(idChat)?.messages?.take(count)
           ?.map { message -> message.apply { viewed = true } }?.toList()
            ?: emptyList()
    }

    fun delChat(idChat: Int): Boolean{
        chats.searchChatId(idChat).apply { return chats.remove(this) }
    }

    fun getChatsByUserId(userId: Int): List<Chat>{
      return  chats.filter { chat -> chat.userFromId == userId ||chat.userToId == userId }.toList()
    }

    fun getUnreadChatsCount(userId: Int): Int{
        return chats.filter { it.userToId == userId || it.userFromId == userId }
            .filter { chat -> chat.messages.any{ message -> !message.viewed } }.count()
    }


    fun printChats() {
        for (chat in chats)
            println(chat)
    }

    fun MutableList<Chat>.searchChatId(idChat: Int): Chat? {
       return chats.find { chat -> chat.idChat == idChat }
    }

}
