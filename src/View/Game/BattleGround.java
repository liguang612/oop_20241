package View.Game;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Controller.GameController;
import Data.AppConstants;
import Data.AppConstants.GameState;
import Utils.Pair;
import View.Game.Widget.PokeHP;
import View.Share.SpriteAnimation;

public class BattleGround extends JPanel implements ActionListener {
	private GameController controller;

	// UI
	private SpringLayout layout;
	private JLabel eGround, pGround, trainer;
	private SpriteAnimation ally, enemy;
	private PokeHP eHP, pHP;

	// Animation
	private Timer timer, resTimer;
	private int posX = 0;
	private int win = 3000, lose = 3000;

	/// 1 -> moveInAll, 2 -> moveInEnemy, -1 -> moveOutAll, -2 -> moveOutEnemy
	private Direction direction = Direction.allMoveIn;

	public BattleGround(GameController controller) {
		super();
		this.controller = controller;

		setLayout(layout = controller.getLayout());
		setOpaque(false);

		ally = controller.getAlly().getAnimationFromBack();
		add(ally);

		enemy = controller.getEnemy().getAnimation();
		add(enemy);

		Pair<ImageIcon, ImageIcon> grounds = AppConstants.getRandomGround();
		eGround = new JLabel();
		add(eGround);
		layout.putConstraint(SpringLayout.NORTH, eGround, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, eGround, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, eGround, posX - AppConstants.SCREEN_WIDTH, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.EAST, eGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, eGround);

		pGround = new JLabel();
		add(pGround);
		layout.putConstraint(SpringLayout.NORTH, pGround, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, pGround, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, pGround, AppConstants.SCREEN_WIDTH - posX, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.EAST, pGround, AppConstants.SCREEN_WIDTH, SpringLayout.WEST, pGround);

		trainer = new JLabel(AppConstants.IMG_TRAINER_MALE);
		add(trainer);
		layout.putConstraint(SpringLayout.NORTH, trainer, AppConstants.SCREEN_HEIGHT * 2 / 5,
				SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, trainer, 0, SpringLayout.WEST, pGround);

		eHP = new PokeHP(controller.getEnemy());
		eHP.setVisible(false);
		add(eHP);
		layout.putConstraint(SpringLayout.NORTH, eHP, 15, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, eHP, 15, SpringLayout.WEST, this);

		pHP = new PokeHP(controller.getAlly());
		pHP.setVisible(false);
		add(pHP);
		layout.putConstraint(SpringLayout.SOUTH, pHP, -30, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, pHP, -30, SpringLayout.EAST, this);

		timer = new Timer(15, this);

		SwingUtilities.invokeLater(() -> {
			final double scale = getWidth() / grounds.first().getIconWidth();

			Image eii = grounds.second().getImage().getScaledInstance(getWidth(), getHeight(),
					Image.SCALE_SMOOTH);
			Image pii = grounds.first().getImage().getScaledInstance(getWidth(), getHeight(),
					Image.SCALE_SMOOTH);
			Image trii = AppConstants.IMG_TRAINER_MALE.getImage().getScaledInstance(
					(int) (AppConstants.IMG_TRAINER_MALE.getIconWidth() * scale),
					(int) (AppConstants.IMG_TRAINER_MALE.getIconHeight() * scale),
					Image.SCALE_SMOOTH);

			eGround.setIcon(new ImageIcon(eii));
			pGround.setIcon(new ImageIcon(pii));
			trainer.setIcon(new ImageIcon(trii));

			setUpConstraints();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int pWidth = AppConstants.SCREEN_WIDTH;

		if (direction == Direction.allMoveIn) {
			layout.putConstraint(SpringLayout.WEST, eGround, pWidth - posX, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.WEST, pGround, posX - pWidth, SpringLayout.WEST, this);

			revalidate();
			repaint();

			if (posX >= pWidth) {
				timer.stop();

				posX = 0;
				ally.startAnimation();
				enemy.startAnimation();

				eHP.setVisible(true);
				pHP.setVisible(true);

				controller.sendMessage(GameState.init);
				direction = null;
			}

			posX += 20;
			if (posX >= pWidth)
				posX = pWidth;
		} else if (direction == Direction.enemyMoveOut) {
			layout.putConstraint(SpringLayout.WEST, eGround, posX, SpringLayout.WEST, this);
			eHP.setVisible(false);

			revalidate();
			repaint();

			if (posX >= pWidth) {
				posX = 0;
				ally.stopAnimation();

				controller.next();

				direction = null;
			}

			posX -= 20;
		} else if (direction == Direction.enemyMoveIn) {
			layout.putConstraint(SpringLayout.WEST, eGround, -posX, SpringLayout.EAST, this);

			revalidate();
			repaint();

			if (posX >= pWidth) {
				timer.stop();

				posX = 0;
				ally.startAnimation();
				enemy.startAnimation();

				eHP.setVisible(true);

				controller.sendMessage(GameState.init);
				direction = null;
			}

			posX += 20;
			if (posX >= pWidth)
				posX = pWidth;
		}
	}

	// Direction == null -> reset UI
	public void setDirection(Direction direction) {
		this.direction = direction;

		if (ally != controller.getAlly().getAnimationFromBack()) {
			if (resTimer != null)
				resTimer.stop();
			ally.setVisible(true);

			remove(ally);
			ally = controller.getAlly().getAnimationFromBack();
			add(ally);

			pHP.setPokemon(controller.getAlly());

			setComponentZOrder(ally, 0);
		}
		if (enemy != controller.getEnemy().getAnimation()) {
			if (resTimer != null)
				resTimer.stop();
			enemy.setVisible(true);

			remove(enemy);
			enemy = controller.getEnemy().getAnimation();
			add(enemy);

			eHP.setPokemon(controller.getEnemy());

			setComponentZOrder(enemy, 0);
		}

		setUpConstraints();

		revalidate();
		repaint();

		if (direction != null) {
			timer.start();
		}
	}

	public Direction getDirection() {
		return direction;
	}

	// Set up constraints from ally, enemy to their ground
	public void setUpConstraints() {
		layout.putConstraint(
				SpringLayout.NORTH, enemy,
				eGround.getHeight() * 65 / 132 - enemy.getHeight() / 2,
				SpringLayout.NORTH, eGround);
		layout.putConstraint(SpringLayout.WEST, enemy,
				eGround.getWidth() * 217 / 320 - enemy.getWidth() / 2,
				SpringLayout.WEST, eGround);

		layout.putConstraint(
				SpringLayout.WEST, ally,
				pGround.getWidth() * 110 / 320 - ally.getWidth() / 2,
				SpringLayout.WEST, pGround);
		layout.putConstraint(
				SpringLayout.SOUTH, ally,
				0,
				SpringLayout.SOUTH, pGround);
	}

	public void updateAlly() {
		pHP.setPokemon(controller.getAlly());
	}

	public void updateEnemy() {
		eHP.setPokemon(controller.getEnemy());
	}

	public void win() {
		resTimer = new Timer(200, (e) -> {
			enemy.setVisible(!enemy.isVisible());
			win -= 200;

			if (win <= 0) {
				enemy.setVisible(false);
				win = 3000;
				resTimer.stop();

				controller.next();
			}
		});

		resTimer.start();
	}

	public void lose() {
		resTimer = new Timer(200, (e) -> {
			ally.setVisible(!ally.isVisible());
			lose -= 200;

			if (lose <= 0) {
				ally.setVisible(false);
				lose = 3000;
				resTimer.stop();
				controller.next();
			}
		});

		resTimer.start();
	}

	public enum Direction {
		allMoveIn,
		enemyMoveIn,
		allMoveOut,
		enemyMoveOut
	}
}
