package domain.actionservice.bll;

import common.*;
import common.Character;
import domain.actionservice.*;
import domain.actionservice.bll.bo.*;
import java.util.Map;

public class ActionBusinessLogicConverterImpl implements ActionBusinessLogicConverter {
    public CharacterAndPlayerBo getCharacterAndPlayerBoFromActionsRequest(ActionsRequest actionsRequest) {
        Character character = actionsRequest.getCharacter();
        Player player = actionsRequest.getPlayer();
        return CharacterAndPlayerBo
                .builder()
                .character(character)
                .player(player)
                .build();
    }

    public ActionAndDiceAndCharacterAndPlayerBo getActionAndDiceAndCharacterAndPlayerBoFromTakeActionRequest(TakeActionRequest takeActionRequest) {
        Action action = takeActionRequest.getAction();
        Die[] dice = takeActionRequest.getDice();
        Character character = takeActionRequest.getCharacter();
        Player player = takeActionRequest.getPlayer();
        return ActionAndDiceAndCharacterAndPlayerBo
                .builder()
                .action(action)
                .dice(dice)
                .character(character)
                .player(player)
                .build();
    }

    public NonStandardActionAndCharacterAndPlayerBo getNonStandardActionAndCharacterAndPlayerBoFromActionFromNonStandardActionRequest(ActionFromNonStandardActionRequest actionFromNonStandardActionRequest) {
        NonStandardAction nonStandardAction = actionFromNonStandardActionRequest.getNonStandardAction();
        Character character = actionFromNonStandardActionRequest.getCharacter();
        Player player = actionFromNonStandardActionRequest.getPlayer();
        return NonStandardActionAndCharacterAndPlayerBo
                .builder()
                .nonStandardAction(nonStandardAction)
                .character(character)
                .player(player)
                .build();
    }

    public ActionAndPlayerBo getActionAndPlayerBoFromUpdatedActionRequest(UpdatedActionRequest updatedActionRequest) {
        Action action = updatedActionRequest.getAction();
        Player player = updatedActionRequest.getPlayer();
        return ActionAndPlayerBo
                .builder()
                .action(action)
                .player(player)
                .build();
    }

    public ActionAndVisibilityAndPlayerBo getActionAndVisibilityAndPlayerBoFromChangeVisibilityOfActionDetailsRequest(ChangeVisibilityOfActionDetailsRequest changeVisibilityOfActionDetailsRequest) {
        Action action = changeVisibilityOfActionDetailsRequest.getAction();
        Map<String, Visibility> visibilityMap = changeVisibilityOfActionDetailsRequest.getVisibilityMap();
        Player player = changeVisibilityOfActionDetailsRequest.getPlayer();
        return ActionAndVisibilityAndPlayerBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public ActionsResponse getActionsResponseFromActionsBo(ActionsBo actionsBo) {
        Action[] actions = actionsBo.getActions();
        return ActionsResponse
                .builder()
                .actions(actions)
                .build();
    }

    public TakeActionResponse getTakeActionResponseFromResultBo(ResultBo resultBo) {
        Result result = resultBo.getResult();
        return TakeActionResponse
                .builder()
                .result(result)
                .build();
    }

    public ActionFromNonStandardActionResponse getActionFromNonStandardActionResponseFromActionBo(ActionBo actionBo) {
        Action action = actionBo.getAction();
        return ActionFromNonStandardActionResponse
                .builder()
                .action(action)
                .build();
    }

    public UpdatedActionResponse getUpdatedActionResponseFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo) {
        Action action = actionAndVisibilityBo.getAction();
        return UpdatedActionResponse
                .builder()
                .action(action)
                .build();
    }

    public ChangeVisibilityOfActionDetailsResponse getChangeVisibilityOfActionDetailsResponseFromActionAndVisibilityBo(ActionAndVisibilityBo actionAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = actionAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfActionDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public ActionAndVisibilityBo getActionAndVisibilityBoFromActionAndVisibilityAndPlayerBo(ActionAndVisibilityAndPlayerBo actionAndVisibilityAndPlayerBo) {
        Action action = actionAndVisibilityAndPlayerBo.getAction();
        Map<String, Visibility> visibilityMap = actionAndVisibilityAndPlayerBo.getVisibilityMap();
        return ActionAndVisibilityBo
                .builder()
                .action(action)
                .visibilityMap(visibilityMap)
                .build();
    }
}