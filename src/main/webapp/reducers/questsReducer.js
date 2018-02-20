import {GET_QUESTS, LOGIN_SUCCESS, LOGOUT, TAKE_QUEST, QUEST_END} from "../constants/actionsType";

const initialState = {
    questsList: [],
    activeQuest: false
};

export default function getQuestsList(state = initialState, action) {
    switch (action.type) {
        case GET_QUESTS:
            return {...state, questsList: action.data};
        case TAKE_QUEST:
            return {...state, activeQuest: false};
        case LOGIN_SUCCESS:
            console.log("login");
            debugger;
            console.log("LOGIN_SUCCESS " + action.data.quest + " inverted " + !action.data.quest);
            return {...state, activeQuest: !action.data.quest};
        case LOGOUT:
            console.log("ACTIVE = FALSE");
            return {...state, activeQuest: false};
        case QUEST_END:
            return {...state, activeQuest: true};
        default:
            return state
    }
}