# PrizePlugin

Working with Arcturus Morningstar 3.0.0

## How to use?
you just need to send:
```:prize <username>```

- update prize list with :update_prize [x] 
- Prize per level (credits, diamonds, duckets, points, badge, furni...)[x]
- Kick user [x]
- User won event BubbleAlert[x]
- Discord logging [x]

## How can i install it?

 1. Download a pre-compiled version. [PrizePlugin](https://github.com/brenoepics/PrizePlugin/releases/)
 2. Run the sql and add badges/texts.
 3. Paste the PrizePlugin-1.0-jar-with-dependencies.jar file into your emulator's plugins folder and start/restart the emulator.
 4. Now you need to give permission for your staffs to use prize command, to do it open your database in the permissions table, and change the permissions cmd_prize and cmd_update_prize.
 5. Then, open prize_plugin table, and configure rewards.
 6. Now, enter your hotel and type: :update_permissions and :update_prize and then try :prize <username>

 7. if you want to configure webhooks check [Config webhook](#Discord)

## configuration

<b>OBS: the level -1 is the default reward if there's none on user's level.</b>

 Permissions:

| Key                  | Default Value |
|----------------------|---------------|
| cmd_prize            | 0             |
| cmd_update_prize     | 0             |

## Discord
![print](https://imgur.com/4VgDJ5c.png)
1. Open your emulator_settings, and change the key `prizeplugin.logging_discord` to 1.  
2. Go to the discord channel, Click the gear icon (Edit Channel) of the channel you want to post to. 
3. Click Webhooks in the left menu. 
4. Click the Create Webhook button. 
5. Enter a Name of your choice. 
6. Click the Copy button of the Webhook URL. 
7. Click the Save button. 
8. Paste the Webhook URL into emulator_settings, key `prizeplugin.logging.discord-webhook.url`


My Discord: BrenoEpic#9671
