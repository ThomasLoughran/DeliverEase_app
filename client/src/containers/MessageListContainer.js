import MessageModal from "../components/MessageModal";
import { useState } from "react";

const MessageListContainer = () => {

    const [openModal, setOpenModal] = useState(false);

    return (
        <aside>
            {openModal && <MessageModal closeModal={setOpenModal} />}
            {!openModal && <button onClick={() => setOpenModal(true)}>Messages</button>}
        </aside>
    );
}

export default MessageListContainer;