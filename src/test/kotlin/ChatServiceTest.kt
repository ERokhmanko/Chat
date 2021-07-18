import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addUser() {
        val service = ChatService()
       val result = service.addUser("Mish")
        assertEquals(1, result)
    }

    @Test
    fun getUsersList() {
        val service = ChatService()
        val user1 = service.addUser("Mish")
        val user2 = service.addUser("Mish2")
        service.addUser("Mish3")

        val result = service.getUsersList()

        val truResult = mutableListOf<User>(User(1, "Mish"), User(2, "Mish2"),
            User(3, "Mish3"))

        assertEquals(truResult, result)
    }

    @Test
    fun addMessNewChat() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
        val result = service.addMess(mess1)

        assertTrue(result)
    }

    @Test
    fun addMessOldChat() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
        service.addMess(mess1)
        val mess2 = Message(idMessage = 0, userFromId = 1, userToId = 3, idChat = 1, text = "tljfdl")
        val result = service.addMess(mess2)

            assertTrue(result)
    }

    @Test
    fun delMessTrue() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
       service.addMess(mess1)

        val result = service.delMess(1, 1)
        assertTrue(result)
    }

    @Test
    fun delMessFalse() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
        service.addMess(mess1)

        val result = service.delMess(2, 8)
        assertFalse(result)
    }

    @Test
    fun editMessTrue() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
        service.addMess(mess1)

        val result = service.editMess(1,1, "edit")

        assertTrue(result)
    }

    @Test
    fun editMessFalse() {
        val service = ChatService()
        val mess1 = Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl")
        service.addMess(mess1)

        val result = service.editMess(2,2, "edit")

        assertFalse(result)
    }

    @Test
    fun getMess() {
        val service = ChatService()
        service.addMess(Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl"))

        val result = service.getMess(1, 1)
        val trueResult = "[Message(idMessage=1, userFromId=1, userToId=2, idChat=1, time=18/07/2021 04:04:15, text=tljfdl, viewed=true)]"

        assertEquals(trueResult, result)
    }

    @Test
    fun delChatTrue() {
        val service = ChatService()
        service.addMess(Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl"))
        service.addMess(Message(idMessage = 0, userFromId = 2, userToId = 4, idChat = 2, text = "tljfdl"))
        service.addMess(Message(idMessage = 0, userFromId = 3, userToId = 5, idChat = 3, text = "tljfdl"))

        val result = service.delChat(1)
        assertTrue(result)
    }

    @Test
    fun delChatFalse() {
        val service = ChatService()
        service.addMess(Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl"))

        val result = service.delChat(2)
        assertFalse(result)
    }

    @Test
    fun getChatsByUserId() { //TODO
    }

    @Test
    fun getUnreadChatsCount() {
        val service = ChatService()
        service.addMess(Message(idMessage = 0, userFromId = 1, userToId = 2, idChat = 1, text = "tljfdl"))

        val result = service.getUnreadChatsCount(1)

        assertEquals(1, result)
    }

    @Test
    fun searchChatId() { //TODO
    }
}