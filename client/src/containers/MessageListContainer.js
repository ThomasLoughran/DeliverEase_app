import MessageModal from "../components/MessageModal";
import { useState } from "react";

const MessageListContainer = () => {

    const [openModal, setOpenModal] = useState(false);




    return (  

        <>
        {openModal && <MessageModal closeModal={setOpenModal}/>}
        {!openModal && <button onClick={() => setOpenModal(true)}>Messages</button>}

        </>

       );
}
 
export default MessageListContainer;